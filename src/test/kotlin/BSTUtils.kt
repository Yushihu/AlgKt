import io.yushihu.tree.binary.common.TreeNode
import io.yushihu.tree.binary.common.bfs
import io.yushihu.tree.binary.common.preorderTraversal
import io.yushihu.tree.binary.search.Tree
import kotlin.random.Random

class BSTUtils {
    fun randSamples(
        size: Int = 1000,
        range: IntRange = 0..50,
    ): List<Int> {
        return List(size) { Random.nextInt(range.first, range.last) }
    }

    fun testCRD(
        tree: Tree<Int, *>,
        samples: List<Int> = randSamples(),
        nonExists: List<Int> = List(10) {
            if (Random.nextBoolean()) Random.nextInt(100, 200) else Random.nextInt(-200, -100)
        },

    ) {
        // insert
        val map = mutableMapOf<Int, Int>()
        samples.shuffled()
            .forEach {
                tree.insert(it)
                map[it] = map.getOrDefault(it, 0) + 1
            }

        // retrieve
        samples.shuffled().forEach { assert(tree.getFirstOrNull(it)?.value == it) }

        // non exists

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

fun <V, T: TreeNode<V, T>> T?.print() {
    bfs().asSequence().map { it?.value }.joinToString(" ").let {
        println(it)
    }
}
