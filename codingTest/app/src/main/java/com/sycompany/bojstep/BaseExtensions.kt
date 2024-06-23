package com.sycompany.bojstep

import java.lang.Math.sqrt

class BaseExtensions {
    fun test() {
        val array = arrayListOf<Int>()
        val arrayStr = arrayListOf<String>()

//===============================================================================================================================================
//[[반복형 함수]]
        array.indices // index들만 모든 array
        array.indices.map { it ->
            //... it 은 index 가 된다. index 기준 반복
        }

        array.forEach { it + 1 } //각 list의 원소들에 대한 액션을 수행한다.
        array.forEachIndexed { index, i -> /*액션*/ } //각 list의 원소들에 대한 액션을 수행한다. (index 를 함께 제공)

//===============================================================================================================================================
//[[필터링 함수]]
        arrayOf("1", "1", "2").distinct() // ["1", "2"]
        //list에서 distinct 를 사용하면 중복인 원소들은 제거되고 순서는 유지된다. List 의 형태를 반환

        arrayOf("a", "A", "b", "c").distinctBy { it.uppercase() } //["A", "B", "C"]
        //해당 조건을 적용한 결과에 대해 중복을 제거하여 반환한다. 첫째 값을 반환한다. List 의 형태를 반환

        array.groupBy { /*조건이나 값*/ }
        // 들어가는 식의 결과 값이 index가 되며 array에서 해당 조건에 만족하는 수들이 값이 된다. /Map<K, List<T>> 형태를 반환
        (0..6).toList().groupBy { it % 2 } // {0=[0,2,4,6], 1=[1,3,5]}
        (0..6).toList().groupBy { it % 2 }[0] // [0,2,4,6]

        arrayOf(1, 2, 3, 4, 5).filter { it % 2 == 0 } // [2,4]
        //리스트를 돌면서 조건(true/false 를 반환하는 조건이어야함)이 true인 값 만을 필터링하여 남긴다. /List<T> 형태를 반환
        arrayOf<Collection<Int>>(
            arrayListOf(1, 2, 3),
            mutableListOf(2, 3),
            setOf(1, 3)
        ).filterIsInstance<List<Int>>() // [[1,2,3], [2,3]]
        //특정한 클래스나 instance 인 경우만을 핉터링하여 남긴다. /List<?> 형태 반환

        arrayOf(1, 2, 3, 4, 5).take(3) // [1,2,3]
        //앞에서부터 n 개수의 값들로 새로운 list 를 만든다. /List<T> 형태 반환
        arrayOf(1, 2, 3, 4, 5).take(3) // [3,4,5]
        //뒤에서부터 n 개수의 값들로 새로운 list 를 만든다. /List<T> 형태 반환
        arrayOf(1, 2, 4, 0, 5).takeWhile { it < 4 } // [1,2]
        //앞에서부터 조건이 false가 될 때까지 새로운 list를 만든다 /List<T> 형태 반환
        arrayOf(1, 2, 4, 0, 5).takeLastWhile { it < 4 } // [0,5]
        //뒤에서부터 조건이 false가 될 때까지 새로운 list를 만든다 /List<T> 형태 반환
        arrayOf(1, 2, 3, 5).takeIf { arr -> arr.all { it < 6 } } // [1,2,3,5]
        // 리스트.takeIf { 조건 } 일 때, 조건에 맞는 경우는 객체 전체를 반환하나, 맞지 않는 경우는 null을 반환 / T? 형태 반환
        // takeIf는 Array 타입에만 사용 가능한 확장 함수가 아닌 모든 타입에 사용 가능함
        "cat".takeIf { it.length > 3 } // null

        arrayOf(1, 2, 3, 4, 5).drop(3) // [4,5]
        //앞에서부터 n개의 원소를 버리고 새로운 list 를 생성한다. /List<T> 형태 반환
        arrayOf(1, 2, 3, 4, 5).dropLast(3) // [1,2]
        //뒤에서부터 n개의 원소를 버리고 새로운 list 를 생성한다. /List<T> 형태 반환
        arrayOf(1, 2, 3, 4, 5).dropWhile { it < 3 } // [3,4,5]
        //조건이 false 가 될 때까지 앞에서부터 리스트를 버린다. /List<T> 형태 반환
        arrayOf(1, 2, 3, 4, 5).dropLastWhile { it > 3 } // [1,2,3]
        //조건이 false 가 될 때까지 뒤에서부터 리스트를 버린다. /List<T> 형태 반환

//===============================================================================================================================================
//[[조건 확인/연산 함수]]
        array.all { it % 2 == 0 }
        //모든 원소가 조건을 만족하는지 체크한다. /Boolean 반환

        array.any { it % 2 == 0 }
        //원소 중, 하나라도 만족하는지 체크한다. /Boolean 반환

        array.count { it % 2 == 0 }
        //조건을 만족하는 개수를 리턴한다. /Int 반환

        array.minOrNull()//최솟값을 반환
        arrayOf(Pair(1, "a"), Pair(2, "b"), Pair(3, "c")).minOf { it.first } // 1
        //해당 R배열 속 요소를 직접 접근하여 min을 구함/R

        array.maxOrNull()//최댓값을 반환
        arrayOf(Pair(1, "a"), Pair(2, "b"), Pair(3, "c")).maxOf { it.first } // 3
        //해당 R배열 속 요소를 직접 접근하여 max를 구함/R

        array.sum()
        arrayOf(1f, 2f, 3f).sum()
        //단순 R 배열의 최종 합을 구한다. /R
        arrayOf(Pair(1, "a"), Pair(2, "b"), Pair(3, "c")).sumOf { it.first } // 6
        arrayOf(Pair(1, "a"), Pair(2, "b"), Pair(3, "c")).sumBy { it.first } // 6
        arrayOf(Pair(1, "a"), Pair(2, "b"), Pair(3, "c")).sumOf { it.first * it.first } // 14
        //해당 R배열 속 요소를 직접 접근하여 합산 (sumOf 와 sumBy는 동일하나, sumBy는 deprecated) /R

//===============================================================================================================================================
//[[정렬 함수]]
        arrayStr.sortWith(compareBy<String> { it.substring(0, 2) }
            .thenBy { it.substring(3, 5) }
            .thenBy { it.substring(6, 8) })
        //정렬을 위해 sortWith 에 comparator 를 넘기는데, comparator는 thenby 로 다음 고려할 조건을 이어붙일 수 있다.

//===============================================================================================================================================
//[[형태 변환형 함수]]
        array.map { it * it }
        arrayOf(1, 2, 3).map { Pair(it.toString(), it) } // [("1",1), ("2",2), ("3",3)]
        //리스트를 돌면서 아예 값을 바꿔버린다. /List<T> -> List<R> 형태를 반환 (아예 타입도 바꿔버림)
        array.mapIndexed { index, i -> Pair(index, i) }
        //index와 함께 리스트를 돌면서 아예 값을 바꿔버린다. /List<T> -> List<R> 형태를 반환 (아예 타입도 바꿔버림)
        var upperCaseAlphabet = mutableListOf<String>()
        arrayOf('a'..'z').mapTo(upperCaseAlphabet) { it.toString().toUpperCase() }
        //특정 MutableCollection에 결과 값을 넣는다 / upperCaseAlphabet = ["A", "B"...., "Z"]
        //mapIndexTo 도 유사

        arrayOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8)
        ).flatMap { it -> it } // [1,2,3,4,5,6,7,8]
        arrayOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8)
        ).flatMap { it: List<Int> -> it.take(2) } // [1,2,4,5,7,8]
        // Collection 내부에 Collection이 들어있을 경우, 내부의 Collection을 펼쳐준다.
        // Collection 을 변환하는 조건에 맞는 값들을 펼쳐준다. /List<선택한 변환> 으로 반환

        arrayOf(
            listOf(1, 2, 3),
            listOf('a', 'b', 'c'),
            listOf(7, 8)
        ).flatMap { it -> it.map { it.toString() } } // [1,2,3,'a','b','c',7,8]

        array.zip(arrayStr) // 두 조합을 합친다. 앞순서가 Pair 의 first가 된다. /List<Pair<T,R>> 를 반환
        listOf<String>("a", "b", "c", "d").zip(
            arrayOf(
                1,
                2,
                3
            )
        ) // [(a,1), (b,2), (c,3)] 만일 두 길이가 차이가 날 경우 작은 것의 size 로 생성된다.

        data class Customer(val id: Int, var name: String, var loc: String)

        val customerList = arrayListOf<Customer>()
        customerList.add(Customer(1, "a", "seoul"))
        customerList.add(Customer(2, "b", "paris"))
        customerList.add(Customer(3, "c", "egypt"))
        val associateByResult1 =
            customerList.associateBy { it.id } //안의 값을 기준으로 Iterable<T> -> K의 연산에 대해 Map<K, T>를 반환 /Map<R, Customer> 형태를 반환
        val associateByResult2 =
            customerList.associateBy(Customer::id, Customer::name) // /Map<id, name> 의 형태를 반환
        val associateByResult3 =
            customerList.associate { it.id to it.name } // /Map<id, name> 의 형태를 반환
    }
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

//root 를 사용하지 않는 판별
fun isPrimeWithoutSqrt(n: Int): Boolean {
    var i = 2
    while (i * i <= n) {
        if (n % i++ == 0) return false
    }
    return true
}

fun binarySearch(arr: IntArray, target: Int): Int {
    var low = 0
    var high = arr.lastIndex
    var mid = 0

    while (low <= high) {
        mid = (low + high) / 2

        when {
            arr[mid] == target -> return mid
            arr[mid] > target -> high = mid - 1
            else -> low = mid + 1
        }
    }
    return -1
}

fun dfsWithCall(
    graph: Array<MutableList<Int>>,
    depthList: ArrayList<Int>,
    check: BooleanArray,
    depth: Int,
    v: Int
) {
    for (node in graph[v]) {
        if (!check[node - 1]) {
            check[node - 1] = true
            depthList.add(depth)
            dfsWithCall(graph, depthList, check, depth, v)
            check[node - 1] = false
        }
    }
}

fun bfsWithRepeat(
    graph: Array<MutableList<Int>>,
    n: Int,
    v: Int
): String { //node starting vertex, graph는 간선을 기준으로 연결된 애들을 갖고있는다.
    graph.forEach {
        it.sort()
    }
    var result = "$v"
    val queue = mutableListOf<Int>()
    val check = Array(n + 1) { false }
    queue.add(v)
    check[v] = true
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        graph[current].forEach {
            if (!check[it]) {
                queue.add(it)
                result += " $it"
                check[it] = true
            }
        }
    }
    return result
}

//graph 에는 각 node 가 Index 형태로 표현됨 (0 노드는 0 번째 arr 에 연결된 node 정보를 mutablelist 로 가짐)
//전체 개수 n
//시작하는 vertex v
fun dfsWithRepeat(graph: Array<MutableList<Int>>, n: Int, v: Int): String {
    graph.forEach {
        it.sortDescending()
    }
    var result = "$v"
    val stack = mutableListOf<Int>() //sequence
    val check = Array(n + 1) { false }
    stack.add(v)
    check[v] = true
    while (stack.isNotEmpty()) {
        val current = stack.removeLast()
        if (!check[current] && current != v) {
            result += " $current"
            check[current] = true
        }
        graph[current].forEach {
            if (!check[it]) {
                stack.add(it)
            }
        }
    }
    return result
}

//
//fun convertPixelsToDp(px:  Float): Float {
//    AppCore.context.resources.displayMetrics.scaledDensity = 4f
//    return px / (AppCore.context.resources
//        .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
//}
//
//inline val Int.toDp: Int
//    get() = TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP,
//        this.toFloat(),
//        Resources.getSystem().displayMetrics
//    ).toInt()
