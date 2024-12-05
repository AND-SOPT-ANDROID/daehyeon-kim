package org.sopt.and.core.extension


fun String?.isJsonObject(): Boolean = this?.let {
    it.startsWith(JSON_OBJECT_START_PREFIX) && endsWith(JSON_OBJECT_END_PREFIX)
} ?: false

fun String?.isJsonArray(): Boolean = this?.let {
    it.startsWith(JSON_ARRAY_START_PREFIX) && it.endsWith(JSON_ARRAY_END_PREFIX)
} ?: false

private const val JSON_OBJECT_START_PREFIX = "{"
private const val JSON_OBJECT_END_PREFIX = "}"
private const val JSON_ARRAY_START_PREFIX = "["
private const val JSON_ARRAY_END_PREFIX = "]"
