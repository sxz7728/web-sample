(function($) {
	$._fullUrl = function(url) {
		return url.charAt(0) == '/' ? globals.APP_NAME + url : url;
	};

	$._ajax = function(options) {
		var opts = jQuery.extend({}, $.uAjax.defaults, options);

		$.ajax({
			type : "post",
			url : $.uFullUrl(opts.url),
			data : opts.params,
			success : function(result) {
				if (result.success) {
					opts.success(result);
				} else {
					if (result.error) {
						alert(result.error);
					}
				}
			}
		});
	};

	$._ajax.defaults = {
		params : {},
		success : function() {
		}
	};

	$.fn._ajaxSubmit = function(options) {

	};

})(jQuery);
