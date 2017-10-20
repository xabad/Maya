// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


fun Long.fileSize(context: Context): String {
    return android.text.format.Formatter.formatFileSize(context, this)
}

fun File.fileSize(context: Context): String {
    return android.text.format.Formatter.formatFileSize(context, this.length())
}

fun File.deleteFile() {
    if (!exists()) return
    if (isDirectory) {
        val files = listFiles() ?: return
        files.forEach { it.deleteFile() }
        delete()
    } else {
        val to = File(absolutePath + System.nanoTime())
        renameTo(to)
        to.delete()
    }
}

/**
 * delete current dir and subs
 */
@Throws(IOException::class)
fun File.drop() {
    val files = listFiles() ?: throw IOException("not a readable directory: " + this)
    files.forEach {
        if (it.isDirectory) {
            it.drop()
        }
        if (!it.delete()) {
            throw IOException("failed to delete file: " + it)
        }
    }
}

fun File.createDirAndFile(): Boolean {
    return if (!exists()) {
        if (parentFile.exists()) {
            createNewFile()
        } else {
            parentFile.mkdirs() && createNewFile()
        }
    } else {
        true
    }
}
fun String.createDirAndFile() = File(this).createDirAndFile()

fun String.writeStreamToFile(inputStream: InputStream){
    inputStream.use {
        FileOutputStream(this).use {
            inputStream.copyTo(it)
        }
    }
}
@Throws(IOException::class)
fun File.forceMkdir() {
    if (exists()) {
        if (!isDirectory) {
            val message = "File " + this + " exists and is not a directory. Unable to create directory."
            throw IOException(message)
        }
    } else {
        if (!mkdirs()) {
            if (!isDirectory) {
                val message = "Unable to create directory " + this
                throw IOException(message)
            }
        }
    }
}

fun String.ifNotExistCreateDir(){
    val file = File(this)
    file.forceMkdir()
}

