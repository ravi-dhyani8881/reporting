package com.mobicart.service.user;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mobicart.model.Address;
import com.mobicart.model.ApiPartner;
import com.mobicart.model.App;
import com.mobicart.model.Authority;
import com.mobicart.model.Billing;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.DiskSpacePricingExample;
import com.mobicart.model.MerchantService;
import com.mobicart.model.Plans;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.UserDetail;
import com.mobicart.util.Pager;
import com.mobicart.util.PaypalResponse;
import com.mobicart.web.account.RegisterForm;

public interface UserService {
	
	/**
	 * Finds a User instance
	 * @param userId {@link User} id
	 * @return {@link User} instance
	 */
	public User find(Long userId);
	
	/**
	 * Finds a User instance
	 * @param email {@link User} email
	 * @return {@link User} instance
	 */
	public User findByEmail(String email);

	/**
	 * Finds a User instance
	 * @param appId {@link App} id
	 * @return {@link User} instance
	 * @throws SQLException
	 */
	public User findByAppId(Long appId) throws SQLException;
	
	/**
	 * Finds a User instance
	 * @param authKey Authentication Key
	 * @return {@link User} instance
	 */
	public User findByAuthKey(String authKey);
	
	/**
	 * Finds an Authority instance
	 * @param username {@link User} username
	 * @return {@link Authority} instance
	 */
	public Authority findAuthority(String username);
	
	/**
	 * Updates an Authority instance
	 * @param authority {@link Authority}
	 */
	public void updateAuthority(Authority authority);
	
	/**
	 * Finds an address instance
	 * @param addressId {@link Address} id
	 * @return {@link Address}
	 */
	public Address findAddress(Long addressId);
	
	/**
	 * Finds an address instance
	 * @param userId {@link User} id
	 * @return {@link Address}
	 */
	public Address findAddressByUserId(Long userId);
	
	/**
	 * Creates an user instance
	 * @param username {@link User} username
	 * @param rawPassword {@link User} password
	 * @return {@link User}
	 * @throws DuplicateUsernameException
	 * @throws SQLException
	 */
	public User create(String username,String rawPassword) throws DuplicateUsernameException,SQLException;
	
	/**
	 * Creates an user instance
	 * @param registerForm {@link RegisterForm} instance
	 * @return {@link User}
	 * @throws DuplicateUsernameException
	 * @throws SQLException
	 */
	public User create(RegisterForm registerForm ) throws DuplicateUsernameException,SQLException;
	
	/**
	 * Updates an user instance
	 * @param user {@link User}
	 * @return boolean
	 */
	public boolean updateUser(User user );
	
	/**
	 * Updates password of a user
	 * @param user {@link User}
	 * @param rawPassword {@link User} password
	 */
	public void updatePassword(User user,String rawPassword);
	
	/**
	 * Creates an address instance
	 * @param address {@link Address}
	 * @return Long {@link Address} id
	 */
	public Long create(Address address);
	
	/**
	 * Saves an address instance
	 * @param address {@link Address}
	 * @return boolean
	 */
	public boolean saveAddress(Address address);
	
	/**
	 * Finds a list of Billing instances
	 * @param userId {@link User} id
	 * @return List of Billing instances
	 * @throws Exception
	 */
	public List<Billing> findBillingDetails(Long userId) throws Exception;
	
	/**
	 * Finds a list of Billing instances
	 * @param userId {@link User} id
	 * @return List of Billing instances
	 * @throws Exception
	 */
	public List<Billing> findAllBillingDetails(Long userId) throws Exception;
	
	/**
	 * Checks whether copyright payment has been done or not
	 * @param userId {@link User} id
	 * @return boolean
	 */
	public boolean checkIfCopyrightPaymentIsMade(Long userId);
		
	/**
	 * Finds a list of user instances
	 * @return List of user instances
	 */
	public List<User> findAllUsers();
	
	/**
	 * Finds a 
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findUsers(Pager pager) throws Exception;
	
	/**
	 * Finds a 
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public List<ApiPartner> findAllPartners(Pager pager) throws Exception;
	
	/**
	 * Finds a 
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public List<ApiPartner> findAllPartners() throws Exception;
	
	/**
	 * Finds a list of user instances
	 * @param keyword 
	 * @return List of user instances
	 */
	public List<User> findUsersByKeyword(String keyword);
	
	/**
	 * Finds a list of user instances
	 * @return List of user instances
	 */
	public List<User> findAdminUsers();
	
	/**
	 * Finds a list of user instances
	 * @param keyword
	 * @return List of user instances
	 */
	public List<User> findAdminUsersByKeyword(String keyword);
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param userId {@link User} Id
	 * @return List of ProductOrder instances
	 */
	public List<ProductOrder> findProductOrders(Long userId);
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param userId {@link User} Id
	 * @return List of ProductOrder instances
	 */
	public List<ProductOrder> findProductOrdersWithPaging(Long userId, Pager pager);
	
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param userId {@link User} Id
	 * @return List of ProductOrder instances
	 */
	public Integer findProductOrdersCountByUser(Long userId);
	
	
	/**
	 * Finds user's APIKey
	 * @param userId {@link User} id
	 * @return String
	 */
	public String findUserAPIKey(Long userId);
	
	/**
	 * Creates a MerchantService instance
	 * @param merchantService {@link MerchantService}
	 * @return Long {@link MerchantService} id
	 * @throws Exception
	 */
	public Long create(MerchantService merchantService) throws Exception;
	
	/**
	 * Finds a list of DiskSpacePricing instances
	 * @param diskSpacePricingExample {@link DiskSpacePricingExample}
	 * @return List of DiskSpacePricing instances
	 * @throws Exception
	 */
	public List<DiskSpacePricing> findDiskSpacePricingByExample(DiskSpacePricingExample diskSpacePricingExample) throws Exception;
	
	/**
	 * Finds an instance of DiskSpacePricing
	 * @param id {@link DiskSpacePricing} id
	 * @return {@link DiskSpacePricing}
	 * @throws Exception
	 */
	public DiskSpacePricing findDiskSpacePricingById(Long id) throws Exception;
	
	/**
	 * Finds a DiskSpacePricing instance
	 * @param fPrice BigDecimal price
	 * @return {@link DiskSpacePricing}
	 * @throws Exception
	 */
	public DiskSpacePricing findDiskSpacePricingByFPrice(BigDecimal fPrice) throws Exception;
	
	/**
	 * Saves PayPal response for a merchant
	 * @param paypalResponse {@link PaypalResponse}
	 * @throws Exception
	 */
	public void saveMerchantOrderDetails(PaypalResponse paypalResponse) throws Exception;
	
	/**
	 * Updates an instance of DiskSpacePricing
	 * @param diskSpacePricing {@link DiskSpacePricing}
	 * @throws Exception
	 */
	public void update(DiskSpacePricing diskSpacePricing) throws Exception;
	
	/**
	 * Saves recurring payment details according to PayPal response
	 * @param paypalResponse {@link PaypalResponse}
	 * @throws Exception
	 */
	public void saveRecurringPaymentDetails(PaypalResponse paypalResponse) throws Exception;

	/**
	 * Finds UserDetail instance 
	 * @param username {@link User} username
	 * @return {@link UserDetail}
	 * @throws Exception
	 */
	public UserDetail findUserDetailByUsername(String username) throws Exception;
	

	/**
	 * Finds User instance 
	 * @param username {@link User} username
	 * @return {@link UserDetail}
	 * @throws Exception
	 */
	public User findUserByDomainURL(String domainURL) throws Exception;
	
	public Plans findServicesByUserId(Long userId) throws Exception;
	public boolean   updateBrandingByUserId(Long userId,boolean val) throws Exception;
	
	
	public String findSatateNameById(Long Id);
	public String findTerritoryCodeById(Long Id);
	
	public Territory findTerritoryByCode(String countryCode) throws SQLException;
	 
	
}
