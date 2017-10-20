// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

fun Int.percent(arg1: Number, len: Int = 0): String {
    return ((this.toDouble() * 100) / arg1.toDouble()).format(len)
}

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)!!

