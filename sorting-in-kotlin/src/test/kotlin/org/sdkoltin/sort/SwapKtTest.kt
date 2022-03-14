package org.sdkoltin.sort

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

internal class SwapKtTest {

	@Test
	fun `test swap for empty list`() {

		val emptyList = mutableListOf<Any>()

		assertThatIllegalStateException().isThrownBy {

			emptyList.swap(0, 1)
		}
	}

	@Test
	fun `test swap for single element list`() {

		val singleElementList = mutableListOf(1)

		assertThatIllegalStateException().isThrownBy {

			singleElementList.swap(0, 1)
		}
	}

	@Test
	fun `test swap for i out of bounds`() {

		val mutableList = mutableListOf(1, 2)

		assertThatIllegalArgumentException().isThrownBy {

			val i = mutableList.size

			mutableList.swap(i, 0)
		}
	}

	@Test
	fun `test swap for i less than zero`() {

		val mutableList = mutableListOf(1, 2)

		assertThatIllegalArgumentException().isThrownBy {

			val i = -1

			mutableList.swap(i, 0)
		}
	}

	@Test
	fun `test swap for j out of bounds`() {

		val mutableList = mutableListOf(1, 2)

		assertThatIllegalArgumentException().isThrownBy {

			val j = mutableList.size

			mutableList.swap(0, j)
		}
	}

	@Test
	fun `test swap for j less than zero`() {

		val mutableList = mutableListOf(1, 2)

		assertThatIllegalArgumentException().isThrownBy {

			val j = -1

			mutableList.swap(0, j)
		}
	}

	@Test
	fun `test swap for i same as j `() {

		val mutableList = mutableListOf(1, 2)

		assertThatIllegalArgumentException().isThrownBy {

			val i = 0
			val j = 0

			mutableList.swap(i, j)
		}
	}
}
