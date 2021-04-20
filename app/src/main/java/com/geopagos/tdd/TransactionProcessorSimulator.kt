package com.geopagos.tdd

//move to test files
class TransactionProcessorSimulator(private val txId: String) : TransactionProcessor {

    private val mProcessCalls = mutableListOf<Pair<Card, Double>>()
    val processCalls: List<Pair<Card, Double>> = mProcessCalls

    override fun process(aCard: Card, amount: Double): String {
        mProcessCalls.add(Pair(aCard, amount))
        return txId
    }

}
