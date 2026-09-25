// LOCALITY_CHECK_ONLY

import org.jetbrains.kotlin.formver.plugin.Borrowed


fun requireLocalFunctionVararg(vararg f: (@Borrowed Any) -> Unit) {}

fun produceLocalFunction(): (@Borrowed Any) -> Unit =
    { _: @Borrowed Any -> <!UNUSED_EXPRESSION!>Unit<!> }

fun produceGlobalFunction(): (Any) -> Unit =
    { _: Any -> <!UNUSED_EXPRESSION!>Unit<!> }

fun produceGlobalLocalFunction(): (Any, @Borrowed Any) -> Unit =
    { _: Any, _: @Borrowed Any -> <!UNUSED_EXPRESSION!>Unit<!> }

fun produceGlobalReceiverFunction(): (Any).() -> Unit =
    { <!UNUSED_EXPRESSION!>Unit<!> }

fun `pass global function as local function vararg argument`() {
    requireLocalFunctionVararg(<!LOCALITY_CONTRACT_MISMATCH!>produceGlobalFunction()<!>)
}

fun `pass global function as second local function vararg argument`() {
    requireLocalFunctionVararg(produceLocalFunction(), <!LOCALITY_CONTRACT_MISMATCH!>produceGlobalFunction()<!>)
}
