package org.sdkotlin.intro.kotlin._05_1_unsigned_types

// Kotlin 1.5 on supports unsigned types and "u/U"-suffixed literals

val uByte: UByte = 255u // or uppercase 255U
val hexUByte: UByte = 0x01u
val binUByte: UByte = 0b0000_0001u
val uShort: UShort = UShort.MAX_VALUE
val uInt = 0u // UInt inferred type if literal fits
val uLong = 0xFF_FF_FF_FF_01u // ULong inferred when literal doesn't fit UInt

// 'lateinit' modifier is not allowed on properties of unsigned types
//lateinit var lateUInt: UInt
