package cn.xabad.maya.extensions

import android.support.v4.util.SimpleArrayMap

/**
 * @author xab
 */


fun <T> Collection<T>?.isNullOrEmpty() = this == null || this.isEmpty()

fun <T> Array<T>?.isNullOrEmpty() = this == null || this.isEmpty()

fun <K,V> SimpleArrayMap<K, V>?.isNullOrEmpty() = this == null || this.isEmpty