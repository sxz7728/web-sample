$.extend({
	utils : {}
});

$.extend($.utils, {
	fullUrl : function(url) {
		return url.charAt(0) == '/' ? globals.APP_NAME + url : url;
	},

	ajax : function(s) {
		s = jQuery.extend({
			params : {}
		}, s);

		$.ajax({
			type : "post",
			url : fullUrl(s.url),
			data : s.params,
			success : function(result) {
				if (result.success) {
					if (s.success) {
						s.success(result);
					}
				} else {
					if (result.error) {
						alert(result.error);
					}
				}
			}
		});
	}
});
