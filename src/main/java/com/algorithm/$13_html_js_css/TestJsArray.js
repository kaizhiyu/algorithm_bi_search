var arr = [{name:'zhang',menuNo:3}, {name:'w',menuNo:2}, {name:'e',menuNo:1}]
function removeByValue(arr, menuNo) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].menuNo == menuNo) {
            arr.splice(i,1);
            break;
        }
    }
}

removeByValue(arr, 2);
// removeByValue(arr, "wed");
console.info(arr)
function compare(o1,o2) {
    if(o1.menuNo <o2.menuNo){
        return 1;
    }else if (o1.menuNo <o2.menuNo){
        return -1;
    }else {
        return 0;
    }
}
arr.sort(compare);
// arr.sort();
console.info(arr);
//arr will now have "mon", "wed", "thur"
