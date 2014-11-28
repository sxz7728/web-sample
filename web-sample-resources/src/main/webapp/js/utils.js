(function($) {
	$._url = function(url) {
		return url.charAt(0) == '/' ? globals.APP_NAME + url : url;
	};

	$._location = function(url) {
		window.location.href = $._url(url);
	};

	$._notify = function(options) {
		var opts = jQuery.extend({}, $._notify.defaults, options);
	};

	$._notify.defaults = {

	};

	$._confirm = function(options) {
		var opts = jQuery.extend({}, $._confirm.defaults, options);
	};

	$._confirm.defaults = {

	};

	$._ajax = function(options) {
		var opts = jQuery.extend({}, $._ajax.defaults, options);

		$.ajax({
			type : "post",
			url : $._url(opts.url),
			data : opts.params,
			dataType : "json",
			success : function(result) {
				if (result.success) {
					opts.success(result);
				} else {
					if (result.error) {
						$._notify({
							message : result.error
						});
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

	$._autoHeight = function() {

	};

	$.fn._data = function() {
		return {};
	};

	$.fn._ajaxSubmit = function(options) {
		var opts = jQuery.extend({}, $.fn._ajaxSubmit.defaults, options);

		$(this).ajaxSubmit({
			type : "post",
			url : $._url(opts.url),
			data : opts.params,
			dataType : "json",
			beforeSubmit : function(arr, $form, options) {
				return $form.valid();
			},

			success : function(result) {
				if (result.success) {
					opts.success(result);
				} else {
					$._notify({
						message : result.error
					});
				}
			}
		});

	};

	$.fn._ajaxSubmit.defaults = {
		params : {},
		success : function() {
		}
	};

})(jQuery);
