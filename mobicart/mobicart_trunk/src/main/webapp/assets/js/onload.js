// Validate for 2 decimal for money
    jQuery.validator.addMethod("decimalTwo", function(value, element) {
        return this.optional(element) || /^(\d{1,8})(\.\d{1,2})?$/.test(value);
    }, "Field value has to be in decimal format");
    
    jQuery.validator.addMethod("noSpecialChar", function(value, element) {
        return this.optional(element) || /^[0-9a-zA-Z\-\_\ ]?$/.test(value);
    }, "Special characters are not allowed");
	
	//Validate for Youtube
    jQuery.validator.addMethod("youtubeUrl", function(value, element) {
        return this.optional(element) || /^http:\/\/(?:www\.)?youtube.com\/watch\?v=\w+(&\S*)?$/.test(value);
    }, "Field value has to be youtube Url");

     jQuery.fn.clearForm = function() {
		  return this.each(function() {
		 var type = this.type, tag = this.tagName.toLowerCase();
		 if (tag == 'form')
		   return jQuery(':input',this).clearForm();
		 if (type == 'text' || type == 'password' || tag == 'textarea')
		   this.value = '';
		 else if (type == 'checkbox' || type == 'radio')
		   this.checked = false;
		 else if (tag == 'select')
		   this.selectedIndex = -1;
		  });
		};

		jQuery.zIndex = jQuery.fn.zIndex = function(opt) {
		    /// <summary>
		    /// Returns the max zOrder in the document (no parameter)
		    /// Sets max zOrder by passing a non-zero number
		    /// which gets added to the highest zOrder.
		    /// </summary>    
		    /// <param name="opt" type="object">
		    /// inc: increment value, 
		    /// group: selector for zIndex elements to find max for
		    /// </param>
		    /// <returns type="jQuery" />
		    var def = { inc: 10, group: "*" };
		    jQuery.extend(def, opt);
		    var zmax = 0;
		    jQuery(def.group).each(function() {
		        var cur = parseInt(jQuery(this).css('z-index'));
		        zmax = cur > zmax ? cur : zmax;
		    });
		    if (!this.jquery)
		        return zmax;

		    return this.each(function() {
		        zmax += def.inc;
		        jQuery(this).css("z-index", zmax);
		    });
		};		

		
		// jQuery.validate.requiredIfVisible.js
		// Copyright (c) 2010 Ori Peleg, http://orip.org, distributed under the MIT license
		(function($) {
		$.validator.addMethod(
		    "requiredIfVisible",
		    function(value, element, params) {
		      function isVisible(e) {
		        // the element and all of its parents must be :visible
		        // inspiration: http://remysharp.com/2008/10/17/jquery-really-visible/
		        return e.is(":visible") && e.parents(":not(:visible)").length == 0;
		      }

		      if (isVisible($(element))) {
		        // call the "required" method
		        return $.validator.methods.required.call(this, $.trim(element.value), element);
		      }

		      return true;
		    },
		    $.validator.messages.required
		  );
		})(jQuery);		
		
		function reset_html(id) {
		    var elem = document.getElementById( id );
		    elem.parentNode.innerHTML = elem.parentNode.innerHTML;
	}
		

/* 
 * Retrieve the video ID from a YouTube video URL
 * @param $ytURL The full YouTube URL from which the ID will be extracted
 * @return $ytvID The YouTube video ID string
 */
function getYTid(ytURL) {

	var ytvIDlen = 11;	// This is the length of YouTube's video IDs

	// The ID string starts after "v=", which is usually right after 
	// "youtube.com/watch?" in the URL
	var idStarts = ytURL.indexOf('?v=');



	// In case the "v=" is NOT right after the "?" (not likely, but I like to keep my 
	// bases covered), it will be after an "&":
	if(idStarts == undefined)
		idStarts = ytURL.indexOf('&v='); 
	// If still FALSE, URL doesn't have a vid ID
	if(idStarts == undefined)
		 return false;

	// Offset the start location to match the beginning of the ID string
	idStarts = idStarts+3;

	// Get the ID string and return it
	var ytvID = ytURL.substring(idStarts, idStarts+ytvIDlen);

	return ytvID;

}

jQuery("#notifications").click(function(){
	 jQuery("#notifications").slideUp("slow");
}); 
