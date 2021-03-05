package org.sdkotlin.kotlindl

import org.jetbrains.kotlinx.dl.api.core.Sequential
import org.jetbrains.kotlinx.dl.api.core.layer.Dense
import org.jetbrains.kotlinx.dl.api.core.layer.Flatten
import org.jetbrains.kotlinx.dl.api.core.layer.Input
import org.jetbrains.kotlinx.dl.api.core.loss.Losses
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.core.optimizer.Adam
import org.jetbrains.kotlinx.dl.datasets.Dataset
import org.jetbrains.kotlinx.dl.datasets.handlers.FASHION_TEST_IMAGES_ARCHIVE
import org.jetbrains.kotlinx.dl.datasets.handlers.FASHION_TEST_LABELS_ARCHIVE
import org.jetbrains.kotlinx.dl.datasets.handlers.FASHION_TRAIN_IMAGES_ARCHIVE
import org.jetbrains.kotlinx.dl.datasets.handlers.FASHION_TRAIN_LABELS_ARCHIVE
import org.jetbrains.kotlinx.dl.datasets.handlers.NUMBER_OF_CLASSES
import org.jetbrains.kotlinx.dl.datasets.handlers.extractFashionImages
import org.jetbrains.kotlinx.dl.datasets.handlers.extractFashionLabels

private const val EPOCHS = 5
private const val TRAINING_BATCH_SIZE = 100
private const val TEST_BATCH_SIZE = 1000
private const val NUM_LABELS = 10
private const val NUM_CHANNELS = 1L
private const val IMAGE_SIZE = 28L

internal val model = Sequential.of(
	Input(IMAGE_SIZE, IMAGE_SIZE, NUM_CHANNELS),
	Flatten(),
	Dense(300),
	Dense(100),
	Dense(NUM_LABELS)
)

fun main() {

	val (train, test) = Dataset.createTrainAndTestDatasets(
		trainFeaturesPath = FASHION_TRAIN_IMAGES_ARCHIVE,
		trainLabelsPath = FASHION_TRAIN_LABELS_ARCHIVE,
		testFeaturesPath = FASHION_TEST_IMAGES_ARCHIVE,
		testLabelsPath = FASHION_TEST_LABELS_ARCHIVE,
		numClasses = NUMBER_OF_CLASSES,
		featuresExtractor = ::extractFashionImages,
		labelExtractor = ::extractFashionLabels
	)

	model.use {

		it.compile(
			optimizer = Adam(),
			loss = Losses.SOFT_MAX_CROSS_ENTROPY_WITH_LOGITS,
			metric = Metrics.ACCURACY
		)

		it.fit(dataset = train, epochs = EPOCHS,
			batchSize = TRAINING_BATCH_SIZE)

		val accuracy = it.evaluate(dataset = test,
			batchSize = TEST_BATCH_SIZE).metrics[Metrics.ACCURACY]

		println("Accuracy: $accuracy")
	}
}
