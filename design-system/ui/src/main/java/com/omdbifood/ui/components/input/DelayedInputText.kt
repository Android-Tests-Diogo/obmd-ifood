package com.omdbifood.ui.components.input

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import java.util.Timer
import java.util.TimerTask

const val DEFAULT_DELAY = 600L

class DelayedInputText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var timer: Timer? = null

    var onUserStopType: (String) -> Unit = {}
    var customDelay: (() -> Long)? = null

    init {
        doOnTextChanged { _, _, _, _ ->
            timer?.cancel()
        }

        doAfterTextChanged {
            timer = Timer()
            timer?.schedule(
                object : TimerTask() {
                    override fun run() {
                        text?.let {
                            if (it.isNotBlank()) {
                                onUserStopType.invoke(text.toString())
                            }
                        }
                    }
                },
                customDelay?.invoke() ?: DEFAULT_DELAY
            )
        }
    }
}
