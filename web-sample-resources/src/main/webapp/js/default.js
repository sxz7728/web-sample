(function($) {
	$._openModify = function(options) {
		var opts = jQuery.extend({}, $._save.defaults, options);
	};

	$._openModify.defaults = {

	};

	$._save = function(options) {
		var opts = jQuery.extend({}, $._save.defaults, options);
	};

	$._save.defaults = {

	};

	$._delete = function(options) {
		var opts = jQuery.extend({}, $._delete.defaults, options);
	};

	$._delete.defaults = {

	};
})(jQuery);

$(function() {
	$.validator.setDefaults({
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			if (element.parent('.input-group').length) {
				error.insertAfter(element.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});

	$("form").validate();
});