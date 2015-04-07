/**
 * Created by JiangWei on 13-12-14.
 */
function nextexpert(){
    if(expert_tween_current + 1 > expert_groups){
        expert_tween_next = 1;
    }else{
        expert_tween_next = expert_tween_current + 1;
    }
    var base = (expert_tween_current - 1)*8;
    var next_base = (expert_tween_next - 1)*8;
    //current easeout
    if(document.getElementById("tween_expert_"+(base+1)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+1)),0.7,{left:1008,delay:0.4});
    }
    if(document.getElementById("tween_expert_"+(base+2)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+2)),0.6,{left:1008,delay:0.3});
    }
    if(document.getElementById("tween_expert_"+(base+3)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+3)),0.5,{left:1008,delay:0.1});
    }
    if(document.getElementById("tween_expert_"+(base+4)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+4)),0.4,{left:1008});
    }
    if(document.getElementById("tween_expert_"+(base+5)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+5)),0.5,{left:1008,delay:0.6});
    }
    if(document.getElementById("tween_expert_"+(base+6)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+6)),0.5,{left:1008,delay:0.5});
    }
    if(document.getElementById("tween_expert_"+(base+7)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+7)),0.5,{left:1008,delay:0.3});
    }
    if(document.getElementById("tween_expert_"+(base+8)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+8)),0.5,{left:1008,delay:0.1});
    }

    //next easein
    for(var i=1;i<9;i++){
        if(document.getElementById("tween_expert_"+(next_base+i)) != null){
            document.getElementById("tween_expert_"+(next_base+i)).style.left = "-300px";
        }
    }

    if(document.getElementById("tween_expert_"+(next_base+1)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+1)),0.5,{left:16,delay:1.1});
    }
    if(document.getElementById("tween_expert_"+(next_base+2)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+2)),0.5,{left:260,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+3)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+3)),0.5,{left:507,delay:0.9});
    }
    if(document.getElementById("tween_expert_"+(next_base+4)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+4)),0.5,{left:752,delay:0.8});
    }
    if(document.getElementById("tween_expert_"+(next_base+5)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+5)),0.5,{left:16,delay:1.1});
    }
    if(document.getElementById("tween_expert_"+(next_base+6)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+6)),0.5,{left:260,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+7)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+7)),0.5,{left:507,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+8)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+8)),0.5,{left:752,delay:0.9});
    }

    expert_tween_current = expert_tween_next;
}
function prevexpert(){
    if(expert_tween_current - 1 == 0){
        expert_tween_next = expert_groups;
    }else{
        expert_tween_next = expert_tween_current - 1;
    }

    var base = (expert_tween_current - 1)*8;
    var next_base = (expert_tween_next - 1)*8;

    //current easeout
    if(document.getElementById("tween_expert_"+(base+1)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+1)),0.4,{left:-300});
    }
    if(document.getElementById("tween_expert_"+(base+2)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+2)),0.5,{left:-300,delay:0.1});
    }
    if(document.getElementById("tween_expert_"+(base+3)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+3)),0.6,{left:-300,delay:0.3});
    }
    if(document.getElementById("tween_expert_"+(base+4)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+4)),0.7,{left:-300,delay:0.4});
    }
    if(document.getElementById("tween_expert_"+(base+5)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+5)),0.5,{left:-300,delay:0.1});
    }
    if(document.getElementById("tween_expert_"+(base+6)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+6)),0.5,{left:-300,delay:0.3});
    }
    if(document.getElementById("tween_expert_"+(base+7)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+7)),0.5,{left:-300,delay:0.5});
    }
    if(document.getElementById("tween_expert_"+(base+8)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(base+8)),0.5,{left:-300,delay:0.6});
    }

    //next easein
    for(var i=1;i<9;i++){
        if(document.getElementById("tween_expert_"+(next_base+i)) != null){
            document.getElementById("tween_expert_"+(next_base+i)).style.left = "1008px";
        }
    }
    if(document.getElementById("tween_expert_"+(next_base+1)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+1)),0.5,{left:16,delay:0.8});
    }
    if(document.getElementById("tween_expert_"+(next_base+2)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+2)),0.5,{left:260,delay:0.9});
    }
    if(document.getElementById("tween_expert_"+(next_base+3)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+3)),0.5,{left:507,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+4)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+4)),0.5,{left:752,delay:1.1});
    }
    if(document.getElementById("tween_expert_"+(next_base+5)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+5)),0.5,{left:16,delay:0.9});
    }
    if(document.getElementById("tween_expert_"+(next_base+6)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+6)),0.5,{left:260,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+7)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+7)),0.5,{left:507,delay:1});
    }
    if(document.getElementById("tween_expert_"+(next_base+8)) != null){
        TweenLite.to(document.getElementById("tween_expert_"+(next_base+8)),0.5,{left:752,delay:1.1});
    }

    expert_tween_current = expert_tween_next;
}

/*免费试播*/

function nextfree(){
    if(free_tween_current + 1 > free_groups){
        free_tween_next = 1;
    }else{
        free_tween_next = free_tween_current + 1;
    }
    var base = (free_tween_current - 1)*8;
    var next_base = (free_tween_next - 1)*8;
    //current easeout
    if(document.getElementById("tween_free_"+(base+1)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+1)),0.7,{left:1008,delay:0.4});
    }
    if(document.getElementById("tween_free_"+(base+2)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+2)),0.6,{left:1008,delay:0.3});
    }
    if(document.getElementById("tween_free_"+(base+3)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+3)),0.5,{left:1008,delay:0.1});
    }
    if(document.getElementById("tween_free_"+(base+4)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+4)),0.4,{left:1008});
    }
    if(document.getElementById("tween_free_"+(base+5)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+5)),0.5,{left:1008,delay:0.6});
    }
    if(document.getElementById("tween_free_"+(base+6)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+6)),0.5,{left:1008,delay:0.5});
    }
    if(document.getElementById("tween_free_"+(base+7)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+7)),0.5,{left:1008,delay:0.3});
    }
    if(document.getElementById("tween_free_"+(base+8)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+8)),0.5,{left:1008,delay:0.1});
    }

    //next easein
    for(var i=1;i<9;i++){
        if(document.getElementById("tween_free_"+(next_base+i)) != null){
            document.getElementById("tween_free_"+(next_base+i)).style.left = "-300px";
        }
    }

    if(document.getElementById("tween_free_"+(next_base+1)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+1)),0.5,{left:0,delay:1.1});
    }
    if(document.getElementById("tween_free_"+(next_base+2)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+2)),0.5,{left:252,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+3)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+3)),0.5,{left:504,delay:0.9});
    }
    if(document.getElementById("tween_free_"+(next_base+4)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+4)),0.5,{left:756,delay:0.8});
    }
    if(document.getElementById("tween_free_"+(next_base+5)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+5)),0.5,{left:0,delay:1.1});
    }
    if(document.getElementById("tween_free_"+(next_base+6)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+6)),0.5,{left:252,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+7)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+7)),0.5,{left:504,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+8)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+8)),0.5,{left:756,delay:0.9});
    }

    free_tween_current = free_tween_next;
}




function prevfree(){
    if(free_tween_current - 1 == 0){
        free_tween_next = free_groups;
    }else{
        free_tween_next = free_tween_current - 1;
    }

    var base = (free_tween_current - 1)*8;
    var next_base = (free_tween_next - 1)*8;

    //current easeout
    if(document.getElementById("tween_free_"+(base+1)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+1)),0.4,{left:-300});
    }
    if(document.getElementById("tween_free_"+(base+2)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+2)),0.5,{left:-300,delay:0.1});
    }
    if(document.getElementById("tween_free_"+(base+3)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+3)),0.6,{left:-300,delay:0.3});
    }
    if(document.getElementById("tween_free_"+(base+4)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+4)),0.7,{left:-300,delay:0.4});
    }
    if(document.getElementById("tween_free_"+(base+5)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+5)),0.5,{left:-300,delay:0.1});
    }
    if(document.getElementById("tween_free_"+(base+6)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+6)),0.5,{left:-300,delay:0.3});
    }
    if(document.getElementById("tween_free_"+(base+7)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+7)),0.5,{left:-300,delay:0.5});
    }
    if(document.getElementById("tween_free_"+(base+8)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(base+8)),0.5,{left:-300,delay:0.6});
    }

    //next easein
    for(var i=1;i<9;i++){
        if(document.getElementById("tween_free_"+(next_base+i)) != null){
            document.getElementById("tween_free_"+(next_base+i)).style.left = "1008px";
        }
    }
    if(document.getElementById("tween_free_"+(next_base+1)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+1)),0.5,{left:0,delay:0.8});
    }
    if(document.getElementById("tween_free_"+(next_base+2)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+2)),0.5,{left:252,delay:0.9});
    }
    if(document.getElementById("tween_free_"+(next_base+3)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+3)),0.5,{left:504,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+4)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+4)),0.5,{left:756,delay:1.1});
    }
    if(document.getElementById("tween_free_"+(next_base+5)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+5)),0.5,{left:0,delay:0.9});
    }
    if(document.getElementById("tween_free_"+(next_base+6)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+6)),0.5,{left:252,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+7)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+7)),0.5,{left:504,delay:1});
    }
    if(document.getElementById("tween_free_"+(next_base+8)) != null){
        TweenLite.to(document.getElementById("tween_free_"+(next_base+8)),0.5,{left:756,delay:1.1});
    }

    free_tween_current = free_tween_next;
}