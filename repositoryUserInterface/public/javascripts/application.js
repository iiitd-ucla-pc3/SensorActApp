$(".forms").hide();
$("ul li a").click(function(){
	var idToShow = $(this).attr("href");
	$(idToShow).show().siblings(".forms").hide();
	return false;
});
$("#sign_up_confirmation").keyup(function(){
	if( $(this).val() != $("#sign_up_password").val() ) {
		$(this).addClass("error").next().text("Passwords don't match.");
	} else {
		$(this).removeClass("error").next().text("");				
	}
});
