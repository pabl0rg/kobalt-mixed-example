package com.guatec

/**
 * Created by juanliska on 3/31/16.
 */
class KotlinClassThatDependsOnJavaClass : JavaBaseClass() {
    override fun doSomething() {
        println("here")
    }
}