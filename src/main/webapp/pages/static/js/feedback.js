var feedback_bool = 0;
$(function(){
    <!--批改报告mouseover弹窗-->
    $(".feedback_survey").mouseover(function(e){
        var objoverlay = $(this).find('.isloading-overlay');
        if(feedback_bool == 1 && !objoverlay.length){
            feedbackareaid = $(this).attr('fbackid');
            $('.feedback_survey').isLoading( "hide" );
            $(this).isLoading({
                text:       "load",
                position:   "overlay"
            });
            $('.feedback_survey').click(function(){
                if(feedback_bool == 1){
                    //弹出对话框
                    feedback_bool = 0;
                    $('.feedback_survey').isLoading( "hide" );
                    $('.fk_tp_box').show();
                }
            });
        }else{
            return;
        }
    })
})