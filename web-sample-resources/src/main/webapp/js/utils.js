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

	$._openDialog = function(options) {
		var opts = jQuery.extend({}, $._openDialog.defaults, options);
	};

	$._openDialog.defaults = {

	};

	$._ajax = function(options) {
		var opts = jQuery.extend({}, $._ajax.defaults, options);

		opts.url = $._url(opts.url);
		opts.data = opts.params;
		opts.dataType = "json";
		opts.success = function(result) {
			if (result.success) {
				opts.success(result);
			} else {
				if (result.error) {
					$._notify({
						message : result.error
					});
				}
			}
		};

		$.ajax(opts);
	};

	$._ajax.defaults = {
		type : "post",
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
		var $this = $(this);

		opts.url = $._url(opts.url);
		opts.data = opts.params;
		opts.dataType = "json";
		opts.beforeSubmit = function(arr, $form, options) {
			return $form.valid();
		};
		opts.success = function(result) {
			if (result.success) {
				opts.success(result);
			} else {
				$._notify({
					message : result.error
				});
			}
		};

		$this.ajaxSubmit(opts);
	};

	$.fn._ajaxSubmit.defaults = {
		type : "post",
		params : {},
		success : function() {
		}
	};

	$.fn._jsonSelect = function(options) {
		var opts = jQuery.extend({}, $.fn._jsonSelect.defaults, options);
		var $this = $(this);

		$this.find("option[value != '']").remove();

		$.each(opts.data, function(i, val) {
			$this.append("<option value='" + val.key + "'>" + val.value
					+ "</option>");
		});

		$this.val(opts.value);

		if ($this.find("option:selected").size() == 0) {
			$this.find("option:first").prop('selected', 'selected');
		}
	};

	$.fn._jsonSelect.defaults = {
		data : [],
		value : ''
	};

	$.fn._ajaxSelect = function(options) {
		var opts = jQuery.extend({}, $.fn._ajaxSelect.defaults, options);
		var $this = $(this);

		opts.success = function(result) {
			opts.data = result;
			$this._jsonSelect(opts);
		};
	};

	$.fn._ajaxSelect.defaults = {};
})(jQuery);
