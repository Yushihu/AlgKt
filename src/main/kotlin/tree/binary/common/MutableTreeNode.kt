package io.yushihu.tree.binary.common

interface MutableTreeNode<
    V,
    T: MutableTreeNode<V, T>,
>: TreeNode<V, T> {
    override val left: T? get() = leftProp
    override val right: T? get() = rightProp
    override val value: V get() = valueProp

    var leftProp: T?
    var rightProp: T?
    var valueProp: V

    fun applyValue(other: T) {
        valueProp = other.value
    }
}
