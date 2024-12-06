package andrewafony.testapp.mangotestchat

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.feature_api.FeatureApi
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi

class FeatureDestinationProvider(
    private val homeFeatureApi: HomeFeatureApi,
    private val settingsFeatureApi: SettingsFeatureApi,
    private val profileFeatureApi: ProfileFeatureApi,
    private val chatFeatureApi: ChatFeatureApi
) {

    fun provide(clazz: Class<out FeatureApi>) : FeatureApi {
        return when(clazz) {
            HomeFeatureApi::class.java -> homeFeatureApi
            SettingsFeatureApi::class.java -> settingsFeatureApi
            ProfileFeatureApi::class.java -> profileFeatureApi
            ChatFeatureApi::class.java -> chatFeatureApi
            else -> throw Exception("No such class $clazz (Feature API)")
        }
    }
}