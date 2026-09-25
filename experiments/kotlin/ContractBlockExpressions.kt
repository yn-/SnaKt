fun preconditions(block: () -> Unit) {
    block()
}

fun example(bool: Boolean) {
    preconditions {
        true
        bool
    }
}
