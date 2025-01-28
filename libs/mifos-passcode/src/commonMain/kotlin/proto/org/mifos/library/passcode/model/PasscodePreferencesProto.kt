package proto.org.mifos.library.passcode.model

import kotlinx.serialization.Serializable

@Serializable
data class PasscodePreferencesProto(
    val passcode: String,
    val hasPasscode: Boolean,
) {
    companion object {
        val DEFAULT = PasscodePreferencesProto(passcode = "", hasPasscode = false)
    }
}
