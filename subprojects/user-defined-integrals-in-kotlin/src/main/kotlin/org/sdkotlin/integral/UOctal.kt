package org.sdkotlin.integral

@JvmInline
value class UOctal internal constructor(
	internal val data: Int,
) : Integral<UOctal> {

	companion object {

		val MIN_VALUE = UOctal(Int.MIN_VALUE)
		val MAX_VALUE = UOctal(Int.MAX_VALUE)
		val ZERO = UOctal(0)
		val ONE = UOctal(1)

		internal fun uOctalRemainder(v1: UOctal, v2: UOctal): UOctal =
			UOctal((v1.toLong() % v2.toLong()).toInt())
	}

	internal constructor(octalValue: String) : this(octalValue.toInt(8))

	override val minValue: UOctal get() = MIN_VALUE
	override val maxValue: UOctal get() = MAX_VALUE
	override val zero: UOctal get() = ZERO
	override val one: UOctal get() = ONE

	override operator fun plus(other: UOctal): UOctal =
		UOctal(data.plus(other.data))

	override operator fun minus(other: UOctal): UOctal =
		UOctal(data.minus(other.data))

	override operator fun unaryMinus(): UOctal =
		UOctal(-data)

	override operator fun rem(other: UOctal): UOctal =
		uOctalRemainder(this, other)

	fun mod(other: UOctal): UOctal = rem(other)

	override fun compareTo(other: UOctal): Int =
		data.compareTo(other.data)

	operator fun rangeTo(other: UOctal): OpenEndRange<UOctal> =
		IntegralRange(this, other)

	operator fun rangeUntil(other: UOctal): OpenEndRange<UOctal> =
		IntegralRange(this, other - other.one)

	fun toUInt(): UInt = data.toUInt()

	fun toInt(): Int = data

	fun toLong(): Long = data.toLong() and 0xFFFF_FFFF

	fun toOctal(): Octal = data.toOctal()

	override fun toString() = "%o".format(data)
}

fun UInt.toUOctal(): UOctal = UOctal(this.toInt())

fun String.toUOctal(): UOctal = UOctal(this)

infix fun UOctal.downTo(to: UOctal): IntegralProgression<UOctal> =
	IntegralProgression.fromClosedRange(this, to, Octal.NEGATIVE_ONE.toUOctal())
