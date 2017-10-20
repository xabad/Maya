// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

import org.json.JSONObject


fun JSONObject.twiceInt(key: String, key1: String, value: Int = 0): Int {
    if (!has(key)) {
        return value
    }
    return try {
        val json = getJSONObject(key)
        json.getInt(key1)
    } catch (e: Exception) {
        value
    }
}
fun JSONObject.twiceLong(key: String, key1: String, value: Long = 0): Long {
    if (!has(key)) {
        return value
    }
    return try {
        val json = getJSONObject(key)
        json.getLong(key1)
    } catch (e: Exception) {
        value
    }
}