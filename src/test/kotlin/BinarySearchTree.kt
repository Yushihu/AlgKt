
import io.yushihu.tree.binary.search.Tree
import io.yushihu.tree.binary.search.TreeNode
import org.junit.jupiter.api.Test
import kotlin.random.Random

class Node(
    left: Node?,
    right: Node?,
    value: Int,
): TreeNode<Int, Node> {
    override var leftProp: Node? = left
    override var rightProp: Node? = right
    override var valueProp: Int = value

}

class Tree: Tree<Int, Node> {
    override var root: Node? = null

    override fun createNode(value: Int): Node {
        return Node(null, null, value)
    }
}

class BSTTest {
    @Test
    fun testCRD() {
        // insert
        val tree = Tree()
        val map = mutableMapOf<Int, Int>()
        val samples = List(100) {
            Random.nextInt(0, 50)
        }
        samples.shuffled()
            .forEach {
                tree.insert(it)
                map[it] = map.getOrDefault(it, 0) + 1
            }

        // retrieve
        samples.shuffled().forEach { assert(tree.getFirstOrNull(it)?.value == it) }

        // non exists
        val nonExists = listOf(112, -132, 211)
        nonExists.forEach { assert(it !in map) }
        nonExists.forEach { assert(tree.getFirstOrNull(it) == null) }

        // delete
        samples.shuffled().forEach {
            tree.deleteFirst(it)
            map[it] = map[it]!! - 1
            if (map.getOrDefault(it, 0) > 0) {
                assert(tree.getFirstOrNull(it)?.value == it)
            } else {
                assert(tree.getFirstOrNull(it) == null)
            }

        }
    }

}
