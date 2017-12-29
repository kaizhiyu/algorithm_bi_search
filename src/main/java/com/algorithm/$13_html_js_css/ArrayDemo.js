var arr = [{name: 'zhang', menuNo: 3}, {name: 'w', menuNo: 2}, {name: 'e', menuNo: 1}]

function removeByValue(arr, menuNo) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].menuNo == menuNo) {
            arr.splice(i, 1);
            break;
        }
    }
}

removeByValue(arr, 2);
// removeByValue(arr, "wed");
// console.info(arr)
function compare(o1, o2) {
    if (o1.menuNo < o2.menuNo) {
        return 1;
    } else if (o1.menuNo < o2.menuNo) {
        return -1;
    } else {
        return 0;
    }
}

arr.sort(compare);
// arr.sort();
arr.splice(0, 1, 'red', 'green');
console.info(arr);


var colors = ["red", "green", "blue"];
var removed = colors.splice(0, 1);  //删除第一项
console.info(colors);  //green,blue
console.info(removed);  //red，返回数组中值包含一项
removed = colors.splice(1, 0, "yellow", "orange");  //从位置1开始插入两项
console.info(colors);  //green,yellow,organge,blue
console.info("返回的removed 数组：");  //green,yellow,organge,blue

console.info(removed);  //返回的是一个空数组
removed = colors.splice(1, 1, "red", "purple");  //插入两项，删除一项
console.info(colors);  //green,red,purple,orange,blue
console.info(removed);  //yellow, 返回的数组中只包含一项

/**
 * 操作数组
 * http://www.jquerycn.cn/a_10447
 */
