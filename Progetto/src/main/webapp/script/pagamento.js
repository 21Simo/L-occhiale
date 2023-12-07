$(window).on('load', function () 
{
    var $preloader = $('#caricamento'),
        $spinner   = $preloader.find('.loader-text');
    $spinner.delay(15000).fadeOut('slow');
    $preloader.delay(15000).fadeOut('slow');
    setTimeout(visibile, 15000);
});

function visibile()
{
	$("#principale").removeClass("principale");
}