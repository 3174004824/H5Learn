$(function(){
	/*
		显示二级菜单
	 */

	/*
		利用ajax实现点击不同标题跳转到不同的页面并在右边显示出来
	 */
    $(".clicktitle").on("click",function(){
        $(this).next().slideToggle(200);//选择当前元素的下一个元素
        //$(".left-one-content").toggleClass("show");
        $(".clicktitle").removeClass("active");
        $(".left-list-content-a").removeClass("active");
        $(this).addClass("active");
    });
    $(".left-list-content-a").on("click",function (){
        $(".left-list-content-a").removeClass("active");
        $(".clicktitle").removeClass("active");
        $(this).addClass("active");
    })

    $(".left-list-content-a").on("click",function(){
        var ajaxurl=$(this).attr("href");//获取要跳转的网页地址

        $.ajax({
            type:"GET",
            url:ajaxurl,
            success:function(html){
                $("#right").html(html);
            }
        });
        return false;//不让a跳转
    });

})