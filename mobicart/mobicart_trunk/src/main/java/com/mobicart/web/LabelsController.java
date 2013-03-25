package com.mobicart.web;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobicart.model.Labels;
import com.mobicart.model.User;
import com.mobicart.service.LabelService;
import com.mobicart.util.CommonUtils;


/**
 * @author dinesh.kumar
 */

@Controller
@RequestMapping("/labels/**")
public class LabelsController {

    @Autowired
    LabelService labelService;

    private static final String PRODUCTS = "products";
    private static final String STORE_DEPARTMENTS = "DepartmentList";
    private static final String DEPARTMENT_CATEGORIES = "CategoryList";
    private static final String HOME_GALLERY_IMAGES = "GalleryImageList";
    private static final String STORE = "store";
    private static final String COUNTRIES = "countries";
    private static final String STATES = "states";
    private static final String PRODUCT_ORDER = "order-details";
    private static final String APP_MERCHANT = "merchant";
    private static final String PRODUCT_ORDER_LIST = "OrderList";
    private static final String PRODUCT_ORDER_LIST_SIZE = "product-order-list-size";
    private static final String SHIPPING = "Shipping";
    private static final String LABEL = "Labels";
    private static final String MY_ACCOUNT_REDIRECT_KEY ="redirect:/store/languages";

    private static final String ERROR = "error";
    private static final String ERRORS = "errors";
    private static final String MESSAGE = "message";


    private static final String VALID = "valid";
    private static final String USER = "user";
//	private static final String ERROR = "error";


    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(LabelsController.class);


    /**
     * Service to save labels
     * 
     * @return Redirect to labels form
     * @throws SQLException 
     */    
    @SuppressWarnings("null")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLabels( ModelMap modelMap, HttpServletRequest request, HttpSession session){
    	User user = (User) request.getSession().getAttribute("user");	
    	HashMap<String, String> labelsMap =null;			
		try {
			labelsMap = labelService.getAllWebLabels(user.getId());
			labelService.deleteByMerchantID(user.getId());			
			for (String key : labelsMap.keySet()) {
				Labels label =new Labels();
			    //System.out.println("Key: " + key + ", Value: " + labelsMap.get(key));
			   // System.out.println(request.getParameter(key));
			    label.setMerchantId(user.getId());		    
			    //label.setLabelKeyId(2);
			    label.setLabelKey(key);
			    
			    if(CommonUtils.replaceNullStringWithEmpty(request.getParameter(key)).trim().equals("")) {
			    	label.setModifiedText(null);
			    } else {
			    	label.setModifiedText(request.getParameter(key));
			    }
			    label.setDateAdded(new Date()); 
				labelService.insertLabels(label);
				session.setAttribute("user", labelService.setDefaultLabelKeyValuesInUser((User)session.getAttribute("user")));
			}
		} catch (SQLException e) {
			logger.error("error",e);
		}catch (Exception e) {
			logger.error("error",e);
		}
		
		
		HashMap<String, String> iphonelabelsMap =null;			
		try {
			iphonelabelsMap = labelService.getAllIphoneLabels(user.getId());						
			for (String key : iphonelabelsMap.keySet()) {
				Labels label =new Labels();
			    //System.out.println("Key: " + key + ", Value: " + labelsMap.get(key));
			   // System.out.println(request.getParameter(key));
			    label.setMerchantId(user.getId());		    
			    //label.setLabelKeyId(2);
			    label.setLabelKey(key);
			    if(CommonUtils.replaceNullStringWithEmpty(request.getParameter(key)).trim().equals("")) { 
			    	label.setModifiedText(null);
			    } else {
			    	label.setModifiedText(request.getParameter(key));
			    }
			    
				labelService.insertLabels(label);
				//session.setAttribute("user", labelService.setDefaultLabelKeyValuesInUser((User)session.getAttribute("user")));
			}
		} catch (SQLException e) {
			logger.error("error",e);
		}catch (Exception e) {
			logger.error("error",e);
		}
		
		modelMap.put("success", "Label's saved successfully.");
		session.setAttribute("success", "Label's saved successfully.");
		//String mydashboard = request.getParameter("key.dashboard.mydashboard");
    	return MY_ACCOUNT_REDIRECT_KEY;
    }
    
    
    

}
