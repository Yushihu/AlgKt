package io.yushihu.tree.binary.avl

import kotlin.math.max
import io.yushihu.tree.binary.search.TreeNode as _TN
import io.yushihu.tree.binary.common.rotateLeft as _rotateLeft
import io.yushihu.tree.binary.common.rotateRight as _rotateRight

interface TreeNode<V: Comparable<V>, T: TreeNode<V, T>>: _TN<V, T> {
    var heightProp: Int
    val height: Int
        get() = heightProp

    val leftHeight get() = leftProp?.height ?: 0
    val rightHeight get() = rightProp?.height ?: 0
}

fun <V: Comparable<V>, T: TreeNode<V, T>> T.rotateLeft(): T {
    val parent = _rotateLeft()
    val left = parent.leftProp!!
    left.heightProp = 1 + max(left.leftHeight, left.rightHeight)
    parent.heightProp = 1 + max(parent.leftHeight, parent.rightHeight)
    return parent
}

fun <V: Comparable<V>, T: TreeNode<V, T>> T.rotateRight(): T {
    val parent = _rotateRight()
    val right = parent.rightProp!!
    right.heightProp = 1 + max(right.leftHeight, right.rightHeight)
    parent.heightProp = 1 + max(parent.leftHeight, parent.rightHeight)
    return parent
}
