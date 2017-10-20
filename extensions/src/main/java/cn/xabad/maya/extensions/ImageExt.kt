// +----------------------------------------------------------------------
// | CreateTime: 2017/10/18 
// +----------------------------------------------------------------------
// | Author:     xab
// +----------------------------------------------------------------------
// | CopyRight:  http://www.xabad.cn
// +----------------------------------------------------------------------

package cn.xabad.maya.extensions

import android.graphics.Bitmap


fun Bitmap.blur(radius: Int): Bitmap {
    val w = this.width
    val h = this.height
    val wm = w - 1
    val hm = h - 1
    val wh = w * h
    val div = radius + radius + 1
    val r = IntArray(wh)
    val g = IntArray(wh)
    val b = IntArray(wh)
    var rSum: Int
    var gSum: Int
    var bSum: Int
    var x: Int
    var y: Int
    var i: Int
    var p: Int
    var p1: Int
    var p2: Int
    var yp: Int
    var yi: Int
    var yw: Int
    val vMin = IntArray(Math.max(w, h))
    val vMax = IntArray(Math.max(w, h))
    val pix = IntArray(w * h)

    this.getPixels(pix, 0, w, 0, 0, w, h)

    val dv = IntArray(256 * div)
    i = 0
    while (i < 256 * div) {
        dv[i] = i / div
        i++
    }

    yi = 0
    yw = yi

    y = 0
    while (y < h) {
        bSum = 0
        gSum = bSum
        rSum = gSum
        i = -radius
        while (i <= radius) {
            p = pix[yi + Math.min(wm, Math.max(i, 0))]
            rSum += p and 0xff0000 shr 16
            gSum += p and 0x00ff00 shr 8
            bSum += p and 0x0000ff
            i++
        }
        x = 0
        while (x < w) {

            r[yi] = dv[rSum]
            g[yi] = dv[gSum]
            b[yi] = dv[bSum]

            if (y == 0) {
                vMin[x] = Math.min(x + radius + 1, wm)
                vMax[x] = Math.max(x - radius, 0)
            }
            p1 = pix[yw + vMin[x]]
            p2 = pix[yw + vMax[x]]

            rSum += (p1 and 0xff0000) - (p2 and 0xff0000) shr 16
            gSum += (p1 and 0x00ff00) - (p2 and 0x00ff00) shr 8
            bSum += (p1 and 0x0000ff) - (p2 and 0x0000ff)
            yi++
            x++
        }
        yw += w
        y++
    }

    x = 0
    while (x < w) {
        bSum = 0
        gSum = bSum
        rSum = gSum
        yp = -radius * w
        i = -radius
        while (i <= radius) {
            yi = Math.max(0, yp) + x
            rSum += r[yi]
            gSum += g[yi]
            bSum += b[yi]
            yp += w
            i++
        }
        yi = x
        y = 0
        while (y < h) {
            pix[yi] = 0xff000000.toInt() or (dv[rSum] shl 16) or (dv[gSum] shl 8) or dv[bSum]
            if (x == 0) {
                vMin[y] = Math.min(y + radius + 1, hm) * w
                vMax[y] = Math.max(y - radius, 0) * w
            }
            p1 = x + vMin[y]
            p2 = x + vMax[y]

            rSum += r[p1] - r[p2]
            gSum += g[p1] - g[p2]
            bSum += b[p1] - b[p2]

            yi += w
            y++
        }
        x++
    }

    this.setPixels(pix, 0, w, 0, 0, w, h)

    return this
}