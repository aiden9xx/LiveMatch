package extension

import android.content.Context
import com.aiden.core.R

val Context.unknownError: String
    get() = getString(R.string.error_something_was_wrong)

val Context.timeoutConnection: String
    get() = getString(R.string.error_time_out)
