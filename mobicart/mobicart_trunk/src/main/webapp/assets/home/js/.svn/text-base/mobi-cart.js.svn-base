
/**/
jQuery(document).ready(function(){
 
	jQuery("#registrationForm").validate({
		rules : {
			storeName : { 
				required:true,
				minlength: 3
			},
			firstName : { 
				required:true,
				minlength: 2
			},
			username : {
				required:true,
				email: true
			},
			
			password : {
				required:true,
				minlength: 5,
				maxlength: 20
			},
			passwordConfirmation : {
				required:true,
				minlength: 5,
				maxlength: 20,	
				equalTo: "#password"
			},
			heardFrom:{
				required:false
			},
			agreeToTerms:{
				required:true,
				minlength: 1

			}
		},
		messages:{
			storeName:{
				required:"Store name is required",
				minlength: "Store name must consist of at least 3 characters"
			},
			firstName:{
					required:"First name is required",
					minlength: "First name must consist of at least 2 characters"
				},
			username: {
				required: "Email address is required",
				email:"It must be a valid email address"
			},
			password: {
				required: "Password is required",
				minlength: "Your password must be at least 5 characters long"
			},
			passwordConfirmation: {
				required: "Confirm password is required",
				minlength: "Your confirm password must be at least 5 characters long",
				equalTo: "Passwords do not match"
			},heardFrom:{
				required:"Sorry this field is required too :)"
			},
			
			
			agreeToTerms: "You must agree to the terms and conditions"
				
		},
		errorElement: "div",
	    wrapper: "div",  // a wrapper around the error message
	    errorPlacement: function(error, element) {
	    	    
	    	    status='false';
	            element.after(error);
	            offset = element.offset();
	            error.css('right', offset.right);
	            error.css('right', offset.right - element.outerHeight());
				},
	success: function(){
	               
	                             

		},
	submitHandler: function(form) {
	mobicartService.summitForm(jQuery(form).attr("id"));
				}

	}) ;
	
	 
	jQuery("#popupContactClose").live('click',function(){
			disablePopup();
			});
 
	jQuery("#backgroundPopup").live('click',function(){
		disablePopup();
			});
 

    jQuery('#popupContact').remove();
    jQuery('body').append('<div id="popupContact" >' 
            +' ' 
            +' '  
            +'<div id="contentArea" align="left" valign="center"> ' 
            +'</div> ' 
            +'</div>  ');

    
    //for thanks page 
    var queryStringList=mobicartService.getUrlVars();
    if(queryStringList.userid){
    	jQuery('#userid').attr("href","account/"+queryStringList.userid+"/send/activationemail");
    }
    if(queryStringList.user){
    	jQuery('#useremail').html(queryStringList.user);
    	
    }
    
    jQuery('#userid').click(function(){
    	
    	 var url=jQuery(this).attr('href')
    	 mobicartService.sendActivationEmail(url);
    	
    });
    $("body").mousemove(function(event) {
    	
    	
    	
    });

});


/*Pop related functions and setting*/
    //SETTING UP OUR POPUP  
    //0 means disabled; 1 means enabled;  
    var popupStatus = 0;  
 
	function loadPopup(){
		//loads popup only if it is disabled
		if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
				});
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
		}
	}

//disabling popup with jQuery magic!
function disablePopup(){
		//disables popup only if it is enabled
		if(popupStatus==1){
			$("#backgroundPopup").fadeOut("slow");
			$("#popupContact").fadeOut("slow");
			popupStatus = 0;
			}
		}

function centerPopup(){
	//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#popupContact").height();
		var popupWidth = $("#popupContact").width();
		//centering
		$("#popupContact").css({
			"position": "absolute",
			"top": windowHeight/2-popupHeight/2+jQuery(window).scrollTop(),
			"left": windowWidth/2-popupWidth/2
			});
		//only need force for IE6
		$("#backgroundPopup").css({
				"height": windowHeight
				});
		
		

}


 


/*service object*/
var mobicartService={
		
	summitForm:function(formId){
		
		 var action=jQuery("#"+formId).attr('action');
		 var formdata=jQuery("#"+formId).serialize();
                 var method=jQuery("#"+formId).attr('method');
		
		jQuery('#contentArea').html('<p style="padding-top:70px;font-size:15px;color:#3D9FD8" align="center">Registering..</p><p align="center"><img src="http://www.mobi-cart.com/static/assets/img/loading.gif"></p>');
             centerPopup();
	     loadPopup();  
					 
		jQuery.ajax({	
			url: action,
  			data: formdata,
			type:method,
			dataType:"text",
  			success: function(value){
    				 
			         //var response = jQuery.parseJSON('{"error":["username may not be empty","firstName may not be empty","firstName length must be between 2 and 50",""],"sucess":""}');
				var response = jQuery.parseJSON(value);
                                 var dynaHTML="";
                 if(response.sucess!="success"){
                	 
                	 if(response.error.length==1){
                		 dynaHTML=dynaHTML+"<h3 style='color:red'>Error</h3>"; 
                		 dynaHTML=dynaHTML+"<p style='color:red'>User already exist.</p>";
                		 
                	 }
                	 
                	 
					jQuery.each(response.error,function(i,n){
					//alert( "Name: " + i + ", Value: " + n );
					dynaHTML=dynaHTML+"<p style='color:red'>"+n+"</p>"
					    
					
					});
					jQuery('#contentArea').html(dynaHTML);
						}
					else{

				//jQuery('#contentArea').html('<p style="padding-top:70px;font-size:25px;color:#3D9FD8" align="center">Successfully registered</p><p>To complete the registration process, please check your email and click on the validation link provided.</p>');
						window.location.href = "./thanks.jsp?user="+response.useremail+"&userid="+response.userid;

					
					}

                                //
				 
                                 
				 //centerPopup();
				 //loadPopup();  
  				},
                        error:function(error){
 
			jQuery('#contentArea').html("Some error has occured.Contact to admin.");	 
			    
			}
			
			
		});
	},
	sendActivationEmail:function(url){
		
		
    jQuery('#sendActivationEmail').html('<img src="http://www.mobi-cart.com/static/assets/img/loading.gif">');				 
	jQuery.ajax({	
		url: url,
		data: "",
		type:"get",
		dataType:"text",
			success: function(value){
				var response = jQuery.parseJSON(value);
				if(response.sucess=="true")
				jQuery('#sendActivationEmail').html("<span style='font-size:15px;color:#3D9FD8'>successfully sent</span>");
				setTimeout(function(){ jQuery('#sendActivationEmail').html(""); }, 5000);
			}
	});
		
		
		
		
		
		
	},
	getUrlVars:function()
	{
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
	}
		
		
		
};
