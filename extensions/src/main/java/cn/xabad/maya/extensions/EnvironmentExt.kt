// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

import android.os.Environment


inline val Environment.isExternalStorageWritable
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

inline val Environment.isExternalStorageReadable
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY || isExternalStorageWritable