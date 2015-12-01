/**
 * Created by liujun on 2015/4/8.
 */
$(document).ready(function(){
    $("ul.sub-menu").hide();
    $(".clickcss").click(function(){
        $(this).next(".sub-menu").slideToggle("slow");
    })
});