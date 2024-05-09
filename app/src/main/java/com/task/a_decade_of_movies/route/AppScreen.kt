package com.edu.academy.core.rout

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    object ComboOfferScreen : AppScreen(ConstantAppScreenName.COMBO_OFFER_SCREEN)
    object RestaurantsScreen : AppScreen(ConstantAppScreenName.RESTAURANTS_SCREEN)
    object RestaurantDetailsScreen : AppScreen(ConstantAppScreenName.RESTAURANT_DETAILS_SCREEN)
    object VariousScreen : AppScreen(ConstantAppScreenName.VARIOUS_SCREEN)
    object BalanceScreen : AppScreen(ConstantAppScreenName.BALANCE_SCREEN)
    object PaymentInformationScreen : AppScreen(ConstantAppScreenName.PAYMENT_INFORMATION_SCREEN)
    object SavedAddressScreen : AppScreen(ConstantAppScreenName.SAVED_ADDRESS_SCREEN)
    object AboutUsScreen : AppScreen(ConstantAppScreenName.ABOUT_US_SCREEN)
    object RateAppScreen : AppScreen(ConstantAppScreenName.RATE_APP_SCREEN)
    object SettingScreen : AppScreen(ConstantAppScreenName.SETTING_SCREEN)
    object TermsOfUserScreen : AppScreen(ConstantAppScreenName.TERMS_OF_USER_SCREEN)
    object PrivacyPolicyScreen : AppScreen(ConstantAppScreenName.PRIVACY_POLICY_SCREEN)
    object HelpAndSupportScreen : AppScreen(ConstantAppScreenName.HELP_AND_SUPPORT_SCREEN)
    object FAQSScreen : AppScreen(ConstantAppScreenName.FAQS_SCREEN)
    object MyCoursesScreen : AppScreen(ConstantAppScreenName.COURSES_SCREEN)
    object ComplaintDetailsScreen : AppScreen(ConstantAppScreenName.COMPLAINTS_DETAILS_SCREEN)
    object ComplaintsScreen : AppScreen(ConstantAppScreenName.COMPLAINT_SCREEN)
    object NotificationToneScreen : AppScreen(ConstantAppScreenName.NOTIFICATION_TONE_SCREEN)
    object PaymentScreen : AppScreen(ConstantAppScreenName.PAYMENT_SCREEN)
    object EditProfileScreen : AppScreen(ConstantAppScreenName.EDIT_PROFILE_SCREEN)
    object PackagesScreen : AppScreen(ConstantAppScreenName.PACKAGES_SCREEN)
    object OnProgressScreen : AppScreen(ConstantAppScreenName.ON_PROGRESS_SCREEN)
    object CanceledOrderScreen : AppScreen(ConstantAppScreenName.CANCELED_ORDER_SCREEN)
    object RateStoreScreen : AppScreen(ConstantAppScreenName.RATE_STORE_SCREEN)
    object RateSpecialOrderScreen : AppScreen(ConstantAppScreenName.RATE_SPECIAL_ORDER_SCREEN)
    object RateRestaurantScreen : AppScreen(ConstantAppScreenName.RATE_RESTAURANT_SCREEN)
    object DeliveredOrderScreen : AppScreen(ConstantAppScreenName.COMPLETER_ORDER_SCREEN)
    object SignInScreen : AppScreen(ConstantAppScreenName.SIGN_IN_SCREEN)
    object SignUpScreen : AppScreen(ConstantAppScreenName.SIGN_UP_SCREEN)
    object ResetPasswordScreen : AppScreen(ConstantAppScreenName.RESET_PASSWORD_SCREEN)
    object ForgetPasswordScreen : AppScreen(ConstantAppScreenName.FORGET_PASSWORD_SCREEN)
    object VerifyScreen : AppScreen(ConstantAppScreenName.VERIFY_SCREEN)
    object MealScreen : AppScreen(ConstantAppScreenName.MEAL_SCREEN)
    object ComboMealScreen : AppScreen(ConstantAppScreenName.COMBO_MEAL_SCREEN)
    object CheckOutScreen : AppScreen(ConstantAppScreenName.CHECK_OUT_SCREEN)
    object AddNewAddressScreen : AppScreen(ConstantAppScreenName.ADD_NEW_Address_SCREEN)
    object ChoosePaymentScreen : AppScreen(ConstantAppScreenName.CHOOSE_PAYMENT_SCREEN)
    object AddPaymentMethodScreen : AppScreen(ConstantAppScreenName.ADD_PAYMENT_METHOD_SCREEN)
    object MarketsScreen : AppScreen(ConstantAppScreenName.MARKETS_SCREEN)
    object MarketDetailsScreen : AppScreen(ConstantAppScreenName.MARKET_DETAILS_SCREEN)
    object SearchScreen : AppScreen(ConstantAppScreenName.SEARCH_SCREEN)
    object FavoritesScreen : AppScreen(ConstantAppScreenName.FAVORITES_SCREEN)
    object AddSpecialOrderScreen : AppScreen(ConstantAppScreenName.ADD_SPECIAL_ORDER_SCREEN)
    object ReceivedOffersScreen : AppScreen(ConstantAppScreenName.RECEIVED_OFFERS_SCREEN)
    object ConfirmYourOrderScreen : AppScreen(ConstantAppScreenName.CONFIRM_YOUR_ORDER_SCREEN)
    object OrderDetailsScreen : AppScreen(ConstantAppScreenName.ORDER_DETAILS_SCREEN)
    object SplashScreen : AppScreen(ConstantAppScreenName.SPLASH_SCREEN)
    object OnBoardingScreen : AppScreen(ConstantAppScreenName.ON_BOARDING_SCREEN)
    object SelectCountryScreen : AppScreen(ConstantAppScreenName.SELECT_COUNTRY_SCREEN)
    object SelectCityScreen : AppScreen(ConstantAppScreenName.SELECT_CITY_SCREEN)
    object MyQuestionScreen : AppScreen(ConstantAppScreenName.MY_QUESTION_SCREEN)
    object AddQuestionScreen : AppScreen(ConstantAppScreenName.ADD_QUESTION_SCREEN)
    object PersonalInformationScreen : AppScreen(ConstantAppScreenName.PERSONAL_INFORMATION_SCREEN)
    object ChangePasswordScreen : AppScreen(ConstantAppScreenName.CHANGE_PASSWORD_SCREEN)
    object ChangeEmailScreen : AppScreen(ConstantAppScreenName.CHANGE_EMAIL_SCREEN)
    object MapScreen : AppScreen(ConstantAppScreenName.MAP_SCREEN)
    object TransferPointsScreen : AppScreen(ConstantAppScreenName.TRANSFER_POINTS_SCREEN)
    object LocationMapScreen : AppScreen(ConstantAppScreenName.LOCATION_MAP_SCREEN)
    object PickLocationMapScreen : AppScreen(ConstantAppScreenName.PICK_LOCATION_MAP_SCREEN)
    object SearchMealsScreen : AppScreen(ConstantAppScreenName.SEARCH_MEALS_SCREEN)
    object VerifyPhoneScreen : AppScreen(ConstantAppScreenName.VERIFY_PHONE_SCREEN)
    object PickLocationHomeMapScreen :
        AppScreen(ConstantAppScreenName.PICK_LOCATION_Home_MAP_SCREEN)

    object SearchProductsScreen : AppScreen(ConstantAppScreenName.SEARCH_PRODUCTS_SCREEN)


    object PickOrderLocationsScreen : AppScreen(ConstantAppScreenName.PICK_ORDER_LOCATIONS_SCREEN)


    object AllHomeVideos : AppScreen(ConstantAppScreenName.ALL_HOME_VIDEOS)
    object VideosPlayerScreen : AppScreen(ConstantAppScreenName.VIDEOS_PLAYER_SCREEN)
    object ChatScreen : AppScreen(ConstantAppScreenName.CHAT_SCREEN)
    object OrderTrackingScreen : AppScreen(ConstantAppScreenName.ORDER_TRACKING_SCREEN)
    object NearFacilitiesMapScreen : AppScreen(ConstantAppScreenName.NEAR_FACILITIES_MAP_SCREEN)
    object VerificationWayScreen : AppScreen(ConstantAppScreenName.VERIFICATION_WAY_SCREEN)
    object OverviewScreen : AppScreen(ConstantAppScreenName.OVERVIEW_SCREEN)
    object ComplainsScreen : AppScreen(ConstantAppScreenName.COMPLAINS_SCREEN)
    object MissionScreen : AppScreen(ConstantAppScreenName.MISSION_SCREEN)
    object RestaurantOrderDetailsScreen : AppScreen(ConstantAppScreenName.RESTAURANT_ORDER_DETAILS_SCREEN)
    object PackageCoursesScreen:AppScreen(ConstantAppScreenName.PACKAGE_COURSES)
    object CourseDetailsScreen:AppScreen(ConstantAppScreenName.COURSE_DETAILS)

}

object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val COMBO_OFFER_SCREEN = "combo_offer_screen"
    const val RESTAURANTS_SCREEN = "restaurants_screen"
    const val RESTAURANT_DETAILS_SCREEN = "restaurant_details_screen"
    const val VARIOUS_SCREEN = "various_screen"
    const val BALANCE_SCREEN = "balance_screen"
    const val PAYMENT_INFORMATION_SCREEN = "payment_information_screen"
    const val SAVED_ADDRESS_SCREEN = "saved_address_screen"
    const val ABOUT_US_SCREEN = "about_us_screen"
    const val RATE_APP_SCREEN = "rate_app_screen"
    const val SETTING_SCREEN = "setting_screen"
    const val TERMS_OF_USER_SCREEN = "terms_of_user_screen"
    const val PRIVACY_POLICY_SCREEN = "privacy_policy_screen"
    const val HELP_AND_SUPPORT_SCREEN = "help_and_support_screen"
    const val FAQS_SCREEN = "faqs_screen"
    const val COURSES_SCREEN = "courses_screen"
    const val COMPLAINTS_DETAILS_SCREEN = "complaints_details_screen"
    const val COMPLAINT_SCREEN = "complaints_screen"
    const val NOTIFICATION_TONE_SCREEN = "notification_tone_screen"
    const val PAYMENT_SCREEN = "payment_screen"
    const val EDIT_PROFILE_SCREEN = "edit_profile_screen"
    const val PACKAGES_SCREEN = "packages_screen"
    const val ON_PROGRESS_SCREEN = "on_progress_screen"
    const val CANCELED_ORDER_SCREEN = "canceled_order_screen"
    const val RATE_RESTAURANT_SCREEN = "rate_restaurant_screen"
    const val COMPLETER_ORDER_SCREEN = "completed_order_screen"
    const val CHECK_OUT_SCREEN = "check_out_screen"
    const val SIGN_IN_SCREEN = "sign_in_screen"
    const val SIGN_UP_SCREEN = "sign_up_screen"
    const val RESET_PASSWORD_SCREEN = "reset_password_screen"
    const val FORGET_PASSWORD_SCREEN = "forget_password_screen"
    const val VERIFY_SCREEN = "verify_screen"
    const val MEAL_SCREEN = "meal_screen"
    const val COMBO_MEAL_SCREEN = "combo_meal_screen"
    const val ADD_NEW_Address_SCREEN = "add_new_address_screen"
    const val CHOOSE_PAYMENT_SCREEN = "choose_payment_screen"
    const val ADD_PAYMENT_METHOD_SCREEN = "add_payment_method_screen"
    const val MARKETS_SCREEN = "markets_screen"
    const val MARKET_DETAILS_SCREEN = "market_details_screen"
    const val SEARCH_SCREEN = "search_screen"
    const val NOTIFICATIONS_SCREEN = "notifications_screen"
    const val FAVORITES_SCREEN = "favorites_screen"
    const val ADD_SPECIAL_ORDER_SCREEN = "add_special_order_screen"
    const val RECEIVED_OFFERS_SCREEN = "received_offers_screen"
    const val CONFIRM_YOUR_ORDER_SCREEN = "confirm_your_order_screen"
    const val ORDER_DETAILS_SCREEN = "order_details_screen"
    const val SPLASH_SCREEN = "splash_screen"
    const val ON_BOARDING_SCREEN = "on_boarding_screen"
    const val SELECT_COUNTRY_SCREEN = "select_country_screen"
    const val SELECT_CITY_SCREEN = "select_city_screen"
    const val MY_QUESTION_SCREEN = "my_question_screen"
    const val ADD_QUESTION_SCREEN = "add_question_screen"
    const val PERSONAL_INFORMATION_SCREEN = "personal_information_screen"
    const val CHANGE_EMAIL_SCREEN = "change_email_screen"
    const val CHANGE_PASSWORD_SCREEN = "change_password_screen"
    const val MAP_SCREEN = "map_screen"
    const val TRANSFER_POINTS_SCREEN = "transfer_points_screen"
    const val LOCATION_MAP_SCREEN = "location_map_screen"
    const val PICK_LOCATION_MAP_SCREEN = "pick_location_map_screen"
    const val SEARCH_MEALS_SCREEN = "search_meals_screen"
    const val RECENT_ADDRESS_BOTTOM_SHEET = "recent_address_bottom_sheet"
    const val CONFIRM_ORDER_BOTTOM_SHEET = "confirm_order_bottom_sheet"
    const val DELIVERY_MAN_IS_ALMOST_HERE_BOTTOM_SHEET = "delivery_man_is_almost_here_bottom_sheet"
    const val PICK_ORDER_LOCATIONS_SCREEN = "pick_order_locations_map_screen"
    const val RATE_STORE_SCREEN = "rate_store_screen"
    const val RATE_SPECIAL_ORDER_SCREEN = "rate_special_order_screen"
    const val PICK_LOCATION_Home_MAP_SCREEN = "pick_location_home_map_screen"
    const val SEARCH_PRODUCTS_SCREEN = "search_products_screen"
    const val VERIFY_PHONE_SCREEN = "verify_phone_screen"
    const val ALL_HOME_VIDEOS = "all_home_videos_screen"
    const val VIDEOS_PLAYER_SCREEN = "videos_player_screen"
    const val CHAT_SCREEN = "chat_screen"
    const val NEAR_FACILITIES_MAP_SCREEN = "near_facilities_map_screen"
    const val ORDER_TRACKING_SCREEN = "order_tracking_screen"
    const val VERIFICATION_WAY_SCREEN = "verification_way_screen"
    const val OVERVIEW_SCREEN = "overview_screen"
    const val COMPLAINS_SCREEN="complains_screen"
    const val MISSION_SCREEN = "mission_screen"
    const val RESTAURANT_ORDER_DETAILS_SCREEN = "restaurant_order_details_screen"
    const val PACKAGE_COURSES = "package_courses"
    const val COURSE_DETAILS="course_details"
}