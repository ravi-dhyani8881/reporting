package com.mobicart.service.user;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobicart.dao.AddressDAO;
import com.mobicart.dao.ApiDao;
import com.mobicart.dao.AppDAO;
import com.mobicart.dao.BillingDAO;
import com.mobicart.dao.DiskSpacePricingDAO;
import com.mobicart.dao.MerchantServiceDAO;
import com.mobicart.dao.ProductOrderDAO;
import com.mobicart.dao.RecurringPaymentDetailDAO;
import com.mobicart.dao.StateDAO;
import com.mobicart.dao.TerritoryDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.dao.impl.ApiPartnerDAO;
import com.mobicart.model.Address;
import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;
import com.mobicart.model.ApiPartner;
import com.mobicart.model.App;
import com.mobicart.model.Authority;
import com.mobicart.model.AuthorityExample;
import com.mobicart.model.Billing;
import com.mobicart.model.BillingExample;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.DiskSpacePricingExample;
import com.mobicart.model.MerchantService;
import com.mobicart.model.Plans;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderExample;
import com.mobicart.model.RecurringPaymentDetail;
import com.mobicart.model.State;
import com.mobicart.model.Territory;
import com.mobicart.model.TerritoryExample;
import com.mobicart.model.User;
import com.mobicart.model.UserDetail;
import com.mobicart.model.UserExample;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.Pager;
import com.mobicart.util.PaypalResponse;
import com.mobicart.web.account.RegisterForm;


@Service
public class ImplUserService implements UserService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ImplUserService.class);
	
	@Autowired
	private UserDAO userDAO = null;
	
	@Autowired
	private AppDAO appDAO = null;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private BillingDAO billingDAO;
	
	@Autowired
	private ApiPartnerDAO apiPartnerDAO;
	
	
	@Autowired
	private ProductOrderDAO productOrderDAO;
	
	@Autowired
	private ApiDao apiDAO;
	
	@Autowired
	private MerchantServiceDAO merchantServiceDAO;
	
	@Autowired
	private DiskSpacePricingDAO diskSpacePricingDAO;
	
	@Autowired
	private RecurringPaymentDetailDAO recurringPaymentDetailDAO;
	
	//@Autowired
	//TerritoryDAO territoryDao;
	@Autowired
	private  TerritoryDAO territoryDAO;
	
	@Autowired
	StateDAO stateDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	public User find(Long userId){
		return userDAO.find(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User findByEmail(String email)  {
		return userDAO.findByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	public User findByAppId(Long appId) throws SQLException {
		App app= appDAO.findAppById(appId);
		User user=find(app.getId());
		return user;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public User findByAuthKey(String authKey) {
		return userDAO.findByAuthKey(authKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public User create(RegisterForm registerForm) throws DuplicateUsernameException,SQLException{
		
		User existingUser=userDAO.findByEmail(registerForm.getUsername());
		
		if( existingUser != null) {
		     throw new DuplicateUsernameException();
		 }
		
			User newUser=new User(registerForm.getUsername());
			newUser.setsFirstName(registerForm.getFirstName());
			newUser.setsLastName(registerForm.getLastName());
			newUser.setPassword(passwordEncoder.encodePassword(registerForm.getPassword(), null));
			Timestamp currentDateTime=DateTimeUtils.getCurrentTimestamp();
			newUser.setdJoinedOn(currentDateTime);
			newUser.setdUpdatedOn(currentDateTime);
			newUser.setPhoneNo(registerForm.getPhoneNo());
			newUser.setsHeardFrom(registerForm.getHeardFrom());
			newUser.setDiskSpacePricingId(1L);
			//enabled by default
			newUser.setEnabled(registerForm.isEnabled());
			newUser.setsActivationKey(registerForm.getAuthKey());
			//newUser.setsCompanyLogo(Constants.DEFAULT_COMPANY_LOGO);
			newUser.setsCompanyLogo(null);
		
			Long userId=userDAO.create(newUser);
			if(userId!=null){
				newUser.setId(userId);
	
				/**Add authority **/
				Authority authority=new Authority();
				authority.setAuthority("ROLE_USER");
				authority.setUsername(registerForm.getUsername());
				authority.setbOrders(false);
				authority.setbUsers(false);
				authority.setbPayment(false);
				authority.setbContent(false);
				userDAO.createAuthority(authority);
			}else{
				newUser=null;
			}
		return newUser;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	public User create(String username, String rawPassword) throws DuplicateUsernameException,SQLException{
		User newUser=new User(username);
		User existingUser=userDAO.findByEmail(username);
		if( existingUser != null) {
		     throw new DuplicateUsernameException();
		 }
		Long userId=userDAO.create(newUser);
		newUser.setId(userId);
		updatePassword(newUser, rawPassword);
		return newUser;
	}
	
	
	
	
	

	/**
	 * {@inheritDoc}
	 */
	public void updatePassword(User user, String rawPassword) {
	    String encodedPassword = passwordEncoder.encodePassword(rawPassword, null);
	    user.setPassword(encodedPassword);
	    user.setEnabled(true);
	    userDAO.updatePassword(user);
	  }

	/**
	 * {@inheritDoc}
	 */
	public Long create(Address address) {
		return addressDAO.save(address);
	}

	/**
	 * {@inheritDoc}
	 */
	public Address findAddress(Long addressId) {
		return addressDAO.findAddressById(addressId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Address findAddressByUserId(Long userId) {
		return addressDAO.findAddressByUserId(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean saveAddress(Address address) {
		boolean retVal=false;
		Address oldAddress=addressDAO.findAddressByUserId(address.getUserId());
		if(oldAddress!=null && oldAddress.getId()!=null){
			retVal=addressDAO.update(address);
		}else{
			addressDAO.save(address);
			retVal=true;
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean updateUser(User user) {
		boolean retVal = false;
		try {
			userDAO.update(user);
			retVal = true;
		} catch (Exception e) {
			logger.error("error",e);
			retVal = false;
		}
		return retVal;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<Billing> findBillingDetails(Long userId) throws Exception{
		BillingExample billingExample=new BillingExample();
		billingExample.createCriteria().andUserIdEqualTo(userId).andFAmountGreaterThan(BigDecimal.ZERO);
		billingExample.setOrderByClause("id DESC");
		return billingDAO.findByExample(billingExample); 
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Billing> findAllBillingDetails(Long userId) throws Exception{
		List<String> typeList = new ArrayList<String>();
		typeList.add("free");
		typeList.add("starter");
		typeList.add("pro");
		BillingExample billingExample=new BillingExample();
		billingExample.createCriteria().andUserIdEqualTo(userId).andSTypeIn(typeList);
		billingExample.setOrderByClause("id DESC");
		return billingDAO.findByExample(billingExample); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean checkIfCopyrightPaymentIsMade(Long userId) {
		BillingExample billingExample=new BillingExample();
		List<String> typeValues =new ArrayList<String>();
		typeValues.add("copyright");
		typeValues.add("resubmissioncopyright");
		typeValues.add("submissioncopyright");
		billingExample.createCriteria().andUserIdEqualTo(userId).andSTypeIn(typeValues); 
		List<Billing> objects=billingDAO.findByExampleWithoutThrows(billingExample); 
		if(objects.size()!=0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> findAllUsers() {
		AuthorityExample authorityExample=new AuthorityExample();
		authorityExample.createCriteria().andAuthorityEqualTo("ROLE_USER");
		authorityExample.setOrderByClause("users.d_joined_on DESC");
		return userDAO.findByAuthorityExample(authorityExample);
	}
		
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> findUsers(Pager pager) throws Exception {
		String userRole="ROLE_USER";
		return userDAO.findUsers(pager, userRole);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ApiPartner> findAllPartners(Pager pager) throws Exception {
		String userRole="ROLE_USER";
		return apiPartnerDAO.findAll(pager);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ApiPartner> findAllPartners() throws Exception {
		String userRole="ROLE_USER";
		return apiPartnerDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> findUsersByKeyword(String keyword) {
		keyword="%"+keyword+"%";
		UserExample userExample=new UserExample();
		UserExample.Criteria critFirstName=userExample.createCriteria().andSFirstNameLike(keyword); 
		UserExample.Criteria critLastName=userExample.createCriteria().andSLastNameLike(keyword); 
		UserExample.Criteria critUsername=userExample.createCriteria().andUsernameLike(keyword); 
		
		userExample.or(critFirstName);
		userExample.or(critLastName);
		userExample.or(critUsername);
		return userDAO.findByExample(userExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> findAdminUsers() {
		AuthorityExample authorityExample=new AuthorityExample();
		authorityExample.createCriteria().andAuthorityEqualTo("ROLE_ADMIN");
		return userDAO.findByAuthorityExample(authorityExample);
	}

	
	public Integer findProductOrdersCountByUser(Long userId){
		ProductOrderExample productOrderExample = new ProductOrderExample();
		productOrderExample.createCriteria().andMerchantIdEqualTo(userId);
		return productOrderDAO.findCountByExample(productOrderExample);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<User> findAdminUsersByKeyword(String keyword) {
		keyword="%"+keyword+"%";
		return userDAO.findAdminUsersByKeyword(keyword);
	}

	/**
	 * {@inheritDoc}
	 */
	public Authority findAuthority(String username) {
		return userDAO.findAuthority(username);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAuthority(Authority authority) {
		userDAO.updateAuthority(authority);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> findProductOrders(Long userId) {
		try{
			ProductOrderExample example=new ProductOrderExample();
			example.createCriteria().andMerchantIdEqualTo(userId);
			example.setOrderByClause("d_order_date DESC");
			List<ProductOrder> returnList = productOrderDAO.findByExample(example);
		
			return returnList;
		
		}catch (Exception e) {
			logger.warn("findProductOrders(Long) - exception ignored", e); //$NON-NLS-1$
			
		}
		return null;
	}
	
	

	
	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> findProductOrdersWithPaging(Long userId, Pager pager) {
		try{
			ProductOrderExample example=new ProductOrderExample();
			example.createCriteria().andMerchantIdEqualTo(userId);
			example.setOrderByClause("d_order_date DESC");
			List<ProductOrder> returnList = productOrderDAO.findByExampleWithPaging(example, pager);
		
			return returnList;
		
		}catch (Exception e) {
			logger.warn("findProductOrders(Long) - exception ignored", e); //$NON-NLS-1$
			
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String findUserAPIKey(Long userId) {
		try{
		
			ApiExample example=new ApiExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<Api> returnList = apiDAO.findByExample(example);
			if(returnList.size() > 0)
			{
				return returnList.get(0).getApiKey();
			}
			else
			{
				return "none";
			}
		
		}catch (Exception e) {
			logger.warn("findUserAPIKey(Long) - exception ignored", e); //$NON-NLS-1$
			
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	public Long create(MerchantService merchantService) throws Exception{
		
		return merchantServiceDAO.save(merchantService);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<DiskSpacePricing> findDiskSpacePricingByExample(
			DiskSpacePricingExample diskSpacePricingExample) throws Exception {
		
		return diskSpacePricingDAO.getDiskSpacePricingByExample(diskSpacePricingExample);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public DiskSpacePricing findDiskSpacePricingById(Long id) throws Exception {
		
		return diskSpacePricingDAO.findDiskSpacePricingById(id);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public DiskSpacePricing findDiskSpacePricingByFPrice(BigDecimal fPrice)
			throws Exception {
		
		return diskSpacePricingDAO.findDiskSpacePricingByFPrice(fPrice);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void saveMerchantOrderDetails(PaypalResponse paypalResponse) throws Exception {
		
		if(paypalResponse.getServiceType()!= null && paypalResponse.getServiceType().equals("branding")){
			paypalResponse.setTransactionAmount(paypalResponse.getSiteConstant().getfRemoveBrandingFee());
			paypalResponse.setPaymentGross(paypalResponse.getSiteConstant().getfRemoveBrandingFee());
		}else if(paypalResponse.getServiceType()!= null && paypalResponse.getServiceType().equals("diskspace")){
			paypalResponse.setPaymentGross(paypalResponse.getDiskSpacePricing().getfPrice());
			paypalResponse.setTransactionAmount(paypalResponse.getDiskSpacePricing().getfPrice());
			
		}
		//Creating Merchant Services
		MerchantService merchantService=new MerchantService();
		merchantService.setAppId(paypalResponse.getAppId());
		merchantService.setfAmount(paypalResponse.getPaymentGross());
		merchantService.setfPaymentFee(paypalResponse.getPaymentFee());
		merchantService.setStoreId(paypalResponse.getUser().getStoreId());
		merchantService.setPayerId(paypalResponse.getPayerId());
		merchantService.setMerchantId(paypalResponse.getUser().getId());
		merchantService.setsRemarks(paypalResponse.getRemarks());
		merchantService.setsPaymentStatus(paypalResponse.getPaymentStatus());
		merchantService.setTxnId(paypalResponse.getTxnId());
		merchantService.setdPaymentDate(paypalResponse.getPaymentDate());
		merchantService.setsServiceType(paypalResponse.getServiceType());
		merchantServiceDAO.save(merchantService);
		
		//Creating Billing Transaction
		
		
		
		// add billing history
		Billing billing = new Billing();
		billing.setAppId(paypalResponse.getAppId());
		billing.setUserId(paypalResponse.getUser().getId());
		billing.setdBillingDate(DateTimeUtils.getCurrentTimestamp());
		billing.setfAmount(paypalResponse.getTransactionAmount());
		billing.setsType(paypalResponse.getServiceType());
		billingDAO.create(billing);
		

	
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void update(DiskSpacePricing diskSpacePricing) throws Exception {
		diskSpacePricingDAO.updateDiskSpacePricing(diskSpacePricing);
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void saveRecurringPaymentDetails(PaypalResponse paypalResponse)
			throws Exception {
		
		//add recurring payment details in case of additional disk space not null
		
		if(paypalResponse.getDiskSpacePricing() !=null){
		RecurringPaymentDetail recurringPaymentDetail=new RecurringPaymentDetail();
		
		recurringPaymentDetail.setAppId(paypalResponse.getAppId());
		recurringPaymentDetail.setdPaymentDate(new Date());
		recurringPaymentDetail.setfAmount(paypalResponse.getDiskSpacePricing().getfPrice());
		recurringPaymentDetail.setfPaymentFee(paypalResponse.getPaymentFee());
		recurringPaymentDetail.setMerchantId(paypalResponse.getUser().getId());
		recurringPaymentDetail.setsRemarks("Recurring payment for Additional disk space pricing.Here the payment fee is including the fees as well.");
		recurringPaymentDetail.setsServiceType("diskspace");
		recurringPaymentDetail.setStoreId(paypalResponse.getUser().getStoreId());
		recurringPaymentDetail.setTxnId(paypalResponse.getTxnId());
		
		recurringPaymentDetailDAO.save(recurringPaymentDetail);
		
		
		}
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	public UserDetail findUserDetailByUsername(String username)
			throws Exception {
		return userDAO.findUserDetailByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
	public User findUserByDomainURL(String domainURL) throws Exception {
		UserExample userExample=new UserExample();
		userExample.createCriteria().andSWebappDomainNameEqualTo(domainURL);
		userExample.or(userExample.createCriteria().andSWebappUrlMappingEqualTo(domainURL));
		List<User> userList=userDAO.findByExample(userExample);
		if(userList!=null && userList.size() > 0){
			return userList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Plans findServicesByUserId(Long userId) throws Exception {
		/*List<String> list=new ArrayList<String>();
		list.add("pro");
		list.add("starter");
		list.add("free");
		
		// TODO Auto-generated method stub
		BillingExample example=new BillingExample();
		example.createCriteria().andUserIdEqualTo(userId)
		.andSTypeIn(list);
		Billing billing=(Billing)billingDAO.findByExample(example);*/
		String stype=billingDAO.findPlansByUserId(userId);
		
		if(stype==null){
			stype="free";
		}
		
		Plans userplan=new Plans();
		if(stype.equals("pro"))
		userplan.setPro(true);
		else if(stype.equals("starter"))
				userplan.setStarter(true);
		else if(stype.equals("free"))
			userplan.setFree(true);
		
		
		//if(billing.)
		return userplan;
	}

	@Override
	public boolean updateBrandingByUserId(Long userId,boolean val) throws Exception {
		 
		  return userDAO.updateBrandingByUserId(userId,val);
	}

	@Override
	public String findSatateNameById(Long Id) {
		State state=(State)stateDao.find(Id);
		return state.getsName();
	}

	@Override
	public String findTerritoryCodeById(Long Id) {
		 Territory territory=(Territory)territoryDAO.findTerritoryById(Id);
		return territory.getsCode();
		//return  null;
	}

	public Territory findTerritoryByCode(String countryCode) throws SQLException{
		
		TerritoryExample territoryExample = new TerritoryExample();
		territoryExample.createCriteria().andSCodeEqualTo(countryCode);
		return territoryDAO.findTerritoriesByExample(territoryExample).get(0);
	} 

	
	
	
	
	
}
