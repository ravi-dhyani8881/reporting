package com.mobicart.web.external;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.ApiBO;
import com.mobicart.bo.Error;
import com.mobicart.bo.Message;
import com.mobicart.model.Address;
import com.mobicart.model.State;
import com.mobicart.model.Store;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.api.UserApi;
import com.mobicart.service.AdminService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.PathLocator;

@Controller
@RequestMapping("/api/**")
public class UserApiWebServiceController {

	@Autowired
    StoreService storeService;
	@Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    ApiBO apiBO;
	
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	private static final String VALID = "valid";
    private static final String USER = "user";
    private static final String APP_MERCHANT = "merchant";
	
	
	
	
	
    /**
     * Service to get Merchant Profile
     *
     * @param user_name
     * @param api_key
     * @return List of Product Orders in JSON or XML
     */

    @RequestMapping(value = "/merchant-profile", method = RequestMethod.GET)
    public ModelAndView getMerchantProfile(
            @RequestParam(value = "user_name", required = true) String userName,
            @RequestParam(value = "api_key", required = true) String apiKey) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        User user = null;


        try {

        	// validate key
        	Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
        	if (!(Boolean) key_response.get(VALID)) {
        		error = (Error) key_response.get(ERROR);// get error
        		mav.addObject(ERROR, error);
        		return mav;
        	}
        	
        	boolean hits = true;
            try{
            	hits = apiBO.validateGeneralApiHits(apiKey);
            }catch(Exception e){
            	mav.addObject(ERROR, error.generateError(1004));
            	return mav;
            }
            
            if(hits == false){
            	mav.addObject(ERROR, error.generateError(1005));
            	return mav;
            }

        	// get user
        	user = (User) key_response.get(USER);


            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
                //e.printStackTrace();
            	}
            
            Address address = null;
            Store store = null;
            try{    
	            address = userService.findAddressByUserId(user.getId());
	            store = storeService.findStoreByUserId(user.getId());
            }catch(Exception e){
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
	            UserApi userApi = new UserApi();
	            userApi.setMerchantId(user.getId());
	            userApi.setFirstName(user.getsFirstName());
	            userApi.setLastName(user.getsLastName());
	            userApi.setMerchantEmail(user.getUsername());
				if(address != null){
					userApi.setAddress(address.getsAddress());
					userApi.setCity(address.getsCity());
					userApi.setState(address.getsState());
					userApi.setZipCode(address.getsZip());
					userApi.setCountry(address.getsCountry());
				}
	        	userApi.setCompanyRegNo(user.getsCompanyRegNo());
	        	userApi.setCompanyTaxRegNo(user.getsTaxRegNo());
	        	userApi.setCompanyLogoUrl(user.getsCompanyLogo());
	        	userApi.setMerchantCurrency(user.getsCurrency());
	        	userApi.setMerchantStoreName(user.getStoreName());
	    		userApi.setPayPalEmailAddress(user.getsPaypalAddress());
	    		userApi.setOrderEmailAddress(store.getsOrderEmail());


            mav.addObject(APP_MERCHANT, userApi);
            return mav;
        
    }

    
    /**
     * Service to update Merchant Profile
     *
     * @param user_name
     * @param api_key
     * @param firstName
     * @param lastName
     * @param companyLogoUrl
     * @param companyWebsite
     * @param payPalAddress
     * @param companyRegNumber
     * @param taxRegNumber
     * @return Update Merchant Profile Status String
     */

    @RequestMapping(value = "/update-merchant-profile", method = RequestMethod.POST)
    public ModelAndView updateMerchantProfile(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "companyLogoUrl", required = false) String companyLogoUrl,
            @RequestParam(value = "companyLogoPath", required = false) MultipartFile companyLogoPath,
            @RequestParam(value = "companyWebsite", required = false) String companyWebsite,
            @RequestParam(value = "payPalAddress", required = false) String payPalAddress,
            @RequestParam(value = "companyRegNumber", required = false) String companyRegNumber,
            @RequestParam(value = "taxRegNumber", required = false) String taxRegNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state_id", required = false) String stateId,
            @RequestParam(value = "country_id", required = false) String countryId,
            @RequestParam(value = "zipcode", required = false) String zipCode,
            @RequestParam(value = "orderEmailAddress", required = false) String orderEmailAddress,
            @RequestParam(value = "storeName", required = false) String storeName) {

        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Address oldAddress = null;
        Store store = null;
        Error error = new Error();
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(firstName) ){
        	missingParamList.add("firstName");
        }
        if(firstName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        if(lastName!=null&&lastName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        if(companyLogoUrl!=null&&companyLogoUrl.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        if(companyWebsite!=null&&companyWebsite.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if( ! StringUtils.isEmpty(companyLogoUrl) ){
			if( ! companyLogoUrl.startsWith("http://")){
				mav.addObject(ERROR, error.generateError(3015));
				return mav;
	    	}
	    		
	    	if(!(companyLogoUrl.endsWith("png") || companyLogoUrl.endsWith("jpg") || companyLogoUrl.endsWith("jpeg")
	    			|| companyLogoUrl.endsWith("bmp") || companyLogoUrl.endsWith("gif"))){
	    		mav.addObject(ERROR, error.generateError(3016));
	    		return mav;
	    	}
		}
		

            try {
                // get user
                user = (User) key_response.get(USER);
                oldAddress = userService.findAddressByUserId(user.getId());
                Long cntryId = null;
                Long stteId = null;
                if (countryId != null && !countryId.equals("")){
                	try{
                		cntryId = Long.parseLong(countryId);
                	}catch(NumberFormatException nfe){
                		mav.addObject(ERROR, error.generateError(2005));
        				return mav;
                	}
                }
                
                if (stateId != null && !stateId.equals("")){
                	try{
                		stteId = Long.parseLong(stateId);
                	}catch(NumberFormatException nfe){
                		mav.addObject(ERROR, error.generateError(2008));
        				return mav;
                	}
                }
                Territory country = storeService.findTerritoryById(cntryId);
                if(country == null){
                	mav.addObject(ERROR, error.generateError(2006));
    				return mav;
                }
                State state = storeService.findStateById(stteId);
                if(state == null){
                	mav.addObject(ERROR, error.generateError(2009));
    				return mav;
                }
                if(state.getTerritoryId().longValue() != country.getId().longValue()){
                	mav.addObject(ERROR, error.generateError(2010));
    				return mav;
                }
                
                
                
                store = storeService.findStoreByUserId(user.getId());
                
                if (firstName != null && !firstName.equals(""))
                    user.setsFirstName(firstName);
                if (lastName != null && !lastName.equals(""))
                    user.setsLastName(lastName);
                if (companyWebsite != null && !companyWebsite.equals(""))
                    user.setsCompanyLogoWebsite(companyWebsite);
                if (payPalAddress != null && !payPalAddress.equals(""))
                    user.setsPaypalAddress(payPalAddress);
                if (companyRegNumber != null && !companyRegNumber.equals(""))
                    user.setsCompanyRegNo(companyRegNumber);
                if (taxRegNumber != null && !taxRegNumber.equals(""))
                    user.setsTaxRegNo(taxRegNumber);
                
                
                if(address != null && !address.equals(""))
                	oldAddress.setsAddress(address);
                if(city != null && !city.equals(""))
                	oldAddress.setsCity(city);
                if(state != null && !state.equals(""))
                	oldAddress.setsState(state.getsName());
                if(zipCode != null && !zipCode.equals(""))
                	oldAddress.setsZip(zipCode);
                if(country != null && !country.equals(""))
                	oldAddress.setsCountry(country.getsCode());
                
                
                if(orderEmailAddress != null && !orderEmailAddress.equals(""))
                	store.setsOrderEmail(orderEmailAddress);
                if(storeName != null && !storeName.equals(""))
                	store.setsSName(storeName);
                if(payPalAddress != null && !payPalAddress.equals(""))
                	store.setsPaypalEmail(payPalAddress);
                try {
                    //user.setsCompanyLogo(companyLogo);

                    if ( ! StringUtils.isEmpty(companyLogoUrl)) {

                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String companyFolderPath = "/company";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath);

                        //hard code
                        String userFolderPath = "/" + user.getId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath + dateWiseFolderPath);

                        String finalCompanyLogoImagePath = mobicartImagesFolderPath
                                + companyFolderPath + userFolderPath + dateWiseFolderPath;
                        
                        int fileNameIndex = companyLogoUrl.lastIndexOf("/");
                		String fileName = companyLogoUrl.substring(fileNameIndex);

                        String companyLogoImageFileName = FileUtils.cleanSpecialChars(fileName);

                        String websiteCompanyLogoImagePath = finalCompanyLogoImagePath + "/" + FileUtils.stuffedFilename(companyLogoImageFileName, "_website");

                        finalCompanyLogoImagePath += "/"
                                + companyLogoImageFileName;

                        
                        URL urlOfImage = null;
                        try {
                        	urlOfImage = new URL(companyLogoUrl);
                        	
                            InputStream companyLogoInputStream = null;
                            OutputStream companyLogoOutputStream = null;
                            OutputStream companyLogoWebsiteOutputStream = null;
                            companyLogoInputStream = urlOfImage.openStream();
                            BufferedImage bufferedImage = ImageIO.read(companyLogoInputStream);
                        	BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage, 220, 90, true);
                        	BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage, 110, 30, true);

                                companyLogoOutputStream = new FileOutputStream(rootPath
                                        + finalCompanyLogoImagePath);
                                companyLogoWebsiteOutputStream = new FileOutputStream(rootPath
                                        + websiteCompanyLogoImagePath);
                                String format = (companyLogoUrl.endsWith(".png")) ? "png" : "jpg";
                                ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
                                ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);


                                ImageUtils.saveImage(iphoneBufferedImage, rootPath
                                        + finalCompanyLogoImagePath);

                                ImageUtils.saveImage(websiteBufferedImage, rootPath
                                        + websiteCompanyLogoImagePath);

                                companyLogoInputStream.close();
                                companyLogoOutputStream.close();
                                companyLogoWebsiteOutputStream.close();
                                user.setsCompanyLogo("/" + finalCompanyLogoImagePath);
                         
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                            mav.addObject(ERROR, error.generateError(8001));
                            return mav;

                        } catch (Exception e) {
                            mav.addObject(ERROR, error.generateError(8001));
                            return mav;
                        }
                    }else if(companyLogoPath != null && companyLogoPath.getSize() > 0){
                    	
                    	MultipartFile companyLogoFile = companyLogoPath;

                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String companyFolderPath = "/company";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath);

                        //hard code
                        String userFolderPath = "/" + user.getId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath + dateWiseFolderPath);

                        String finalCompanyLogoImagePath = mobicartImagesFolderPath
                                + companyFolderPath + userFolderPath + dateWiseFolderPath;

                        String companyLogoImageFileName = FileUtils.cleanSpecialChars(companyLogoFile.getOriginalFilename());

                        String websiteCompanyLogoImagePath = finalCompanyLogoImagePath + "/" + FileUtils.stuffedFilename(companyLogoImageFileName, "_website");

                        finalCompanyLogoImagePath += "/"
                                + companyLogoImageFileName;

                        try {
                            InputStream companyLogoInputStream = null;
                            OutputStream companyLogoOutputStream = null;
                            OutputStream companyLogoWebsiteOutputStream = null;
                            if (companyLogoFile.getSize() > 0) {
                                companyLogoInputStream = companyLogoFile.getInputStream();
                                BufferedImage bufferedImage = ImageIO
                                        .read(companyLogoInputStream);
                                BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage,
                                        220, 90, true);

                                BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage,
                                        110, 30, true);

                                companyLogoOutputStream = new FileOutputStream(rootPath
                                        + finalCompanyLogoImagePath);
                                companyLogoWebsiteOutputStream = new FileOutputStream(rootPath
                                        + websiteCompanyLogoImagePath);
                                String format = (companyLogoFile.getOriginalFilename()
                                        .endsWith(".png")) ? "png" : "jpg";
                                ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
                                ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);


                                ImageUtils.saveImage(iphoneBufferedImage, rootPath
                                        + finalCompanyLogoImagePath);

                                ImageUtils.saveImage(websiteBufferedImage, rootPath
                                        + websiteCompanyLogoImagePath);

                                companyLogoInputStream.close();
                                companyLogoOutputStream.close();
                                companyLogoWebsiteOutputStream.close();
                                user.setsCompanyLogo("/" + finalCompanyLogoImagePath);
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                            String errorMessage = "Some error occured saving company image";
                            error.setMessage(errorMessage);
                            mav.addObject(ERROR, error);
                            return mav;

                        } catch (Exception e) {
                            String errorMessage = "Some error occured saving company image";
                            error.setMessage(errorMessage);
                            mav.addObject(ERROR, error);
                            return mav;
                        }
                    }
                    adminService.updateMercahntProfile(user);
                    storeService.updateAddress(oldAddress);
                    storeService.updateStore(store);
                } catch (Exception e) {
                    mav.addObject(ERROR, error.generateError(1004));
                    return mav;
                }

            } catch (Exception e) {
                mav.addObject(ERROR, error.generateError(1004));
                return mav;
            }

            message.setMessage("Profile Updated Successfully.");
            mav.addObject(MESSAGE, message);
            return mav;
        
    }


}
