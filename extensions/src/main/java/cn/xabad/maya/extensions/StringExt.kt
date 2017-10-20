// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

import java.lang.StringBuilder


fun CharSequence?.startsWithAny(vararg rex: String): Boolean {
    if (this.isNullOrEmpty()) return false
    rex.forEach {
        if (this!!.startsWith(it)) return true
    }
    return false
}

fun CharSequence?.containsAny(vararg rex: String): Boolean {
    if (this.isNullOrEmpty()) return false
    rex.forEach {
        if (this!!.contains(it)) return true
    }
    return false
}

fun CharSequence.stripEmoji(): String {
    val builder = StringBuilder()
    val count = Character.codePointCount(this, 0, length)
    (0 until count)
        .map { Character.codePointAt(this, it) }
        .filter { Character.charCount(it) > 1 }
        .forEach { builder.appendCodePoint(it) }
    return builder.toString()
}