def composite(n :Int): List[Int] = {
    def isComposite(i: Int): Boolean = {
        var res: Boolean = false
        for (j <- 2 to Math.sqrt(i).toInt; if i % j == 0) res = true
        res 
    }

    for (x <- List.range(2, n + 1); if isComposite(x)) yield x
}

List.range(2, 11).foreach(x=> println(composite(x)))