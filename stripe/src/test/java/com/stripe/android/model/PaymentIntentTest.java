package com.stripe.android.model;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class PaymentIntentTest {

    private static final String PAYMENT_INTENT_WITH_SOURCE_JSON = "{\n" +
            "  \"id\": \"pi_1CkiBMLENEVhOs7YMtUehLau\",\n" +
            "  \"object\": \"payment_intent\",\n" +
            "  \"payment_method_types\": [\n" +
            "    \"card\"\n" +
            "  ],\n" +
            "  \"amount\": 1000,\n" +
            "  \"canceled_at\": 1530839340,\n" +
            "  \"capture_method\": \"automatic\",\n" +
            "  \"client_secret\": \"pi_1CkiBMLENEVhOs7YMtUehLau_secret_s4O8SDh7s6spSmHDw1VaYPGZA\",\n" +
            "  \"confirmation_method\": \"publishable\",\n" +
            "  \"created\": 1530838340,\n" +
            "  \"currency\": \"usd\",\n" +
            "  \"description\": \"Example PaymentIntent charge\",\n" +
            "  \"livemode\": false,\n" +
            "  \"next_action\": null,\n" +
            "  \"receipt_email\": null,\n" +
            "  \"shipping\": null,\n" +
            "  \"source\": \"src_1CkiC3LENEVhOs7YMSa4yx4G\",\n" +
            "  \"status\": \"succeeded\"\n" +
            "}\n";

    private static final String BAD_URL = "nonsense-blahblah";

    private static final String PAYMENT_INTENT_WITH_SOURCE_WITH_BAD_AUTH_URL_JSON = "{\n" +
            "  \"id\": \"pi_1CkiBMLENEVhOs7YMtUehLau\",\n" +
            "  \"object\": \"payment_intent\",\n" +
            "  \"amount\": 1000,\n" +
            "  \"canceled_at\": 1530839340,\n" +
            "  \"capture_method\": \"automatic\",\n" +
            "  \"client_secret\": \"pi_1CkiBMLENEVhOs7YMtUehLau_secret_s4O8SDh7s6spSmHDw1VaYPGZA\",\n" +
            "  \"confirmation_method\": \"publishable\",\n" +
            "  \"created\": 1530838340,\n" +
            "  \"currency\": \"usd\",\n" +
            "  \"description\": \"Example PaymentIntent charge\",\n" +
            "  \"livemode\": false,\n" +
            "  \"next_action\": {" +
            "       \"type\": \"redirect_to_url\"," +
            "           \"redirect_to_url\": {" +
            "             \"url\": \""+ BAD_URL +"\"," +
            "             \"return_url\": \"yourapp://post-authentication-return-url\"" +
            "           } " +
            "       },\n" +
            "  \"receipt_email\": null,\n" +
            "  \"shipping\": null,\n" +
            "  \"source\": \"src_1CkiC3LENEVhOs7YMSa4yx4G\",\n" +
            "  \"status\": \"requires_action\"\n" +
            "}\n";

    private static final String PAYMENT_INTENT_WITH_PAYMENT_METHODS_JSON = "{\n" +
            "  \"id\": \"pi_Aabcxyz01aDfoo\",\n" +
            "  \"object\": \"payment_intent\",\n" +
            "  \"amount\": 750,\n" +
            "  \"amount_capturable\": 0,\n" +
            "  \"amount_received\": 750,\n" +
            "  \"application\": null,\n" +
            "  \"application_fee_amount\": null,\n" +
            "  \"canceled_at\": null,\n" +
            "  \"cancellation_reason\": null,\n" +
            "  \"capture_method\": \"automatic\",\n" +
            "  \"charges\": {\n" +
            "    \"object\": \"list\",\n" +
            "    \"data\": [],\n" +
            "    \"has_more\": false,\n" +
            "    \"total_count\": 0,\n" +
            "    \"url\": \"/v1/charges?payment_intent=pi_Aabcxyz01aDfoo\"\n" +
            "  },\n" +
            "  \"client_secret\": null,\n" +
            "  \"confirmation_method\": \"publishable\",\n" +
            "  \"created\": 123456789,\n" +
            "  \"currency\": \"usd\",\n" +
            "  \"customer\": null,\n" +
            "  \"description\": \"PaymentIntent Description\",\n" +
            "  \"last_payment_error\": null,\n" +
            "  \"livemode\": false,\n" +
            "  \"metadata\": {\n" +
            "    \"order_id\": \"123456789\"\n" +
            "  },\n" +
            "  \"next_action\": null,\n" +
            "  \"on_behalf_of\": null,\n" +
            "  \"payment_method\": null,\n" +
            "  \"payment_method_types\": [\n" +
            "    \"card\"\n" +
            "  ],\n" +
            "  \"receipt_email\": \"jenny@example.com\",\n" +
            "  \"review\": null,\n" +
            "  \"shipping\": {\n" +
            "    \"address\": {\n" +
            "      \"city\": \"Stockholm\",\n" +
            "      \"country\": \"Sweden\",\n" +
            "      \"line1\": \"Mega street 5\",\n" +
            "      \"line2\": \"Mega street 5\",\n" +
            "      \"postal_code\": \"12233JJHH\",\n" +
            "      \"state\": \"NYC\"\n" +
            "    },\n" +
            "    \"carrier\": null,\n" +
            "    \"name\": \"Mohit  Name\",\n" +
            "    \"phone\": null,\n" +
            "    \"tracking_number\": null\n" +
            "  },\n" +
            "  \"source\": \"src_1E884r2eZvKYlo2CTft0qEyY\",\n" +
            "  \"statement_descriptor\": \"PaymentIntent Statement Descriptor\",\n" +
            "  \"status\": \"succeeded\",\n" +
            "  \"transfer_data\": null,\n" +
            "  \"transfer_group\": null\n" +
            "}";

    private static final String PARTIAL_PAYMENT_INTENT_WITH_REDIRECT_URL_JSON = "{\n" +
            "\t\"id\": \"pi_Aabcxyz01aDfoo\",\n" +
            "\t\"object\": \"payment_intent\",\n" +
            "\t\"status\": \"requires_action\",\n" +
            "\t\"next_action\": {\n" +
            "\t\t\"type\": \"redirect_to_url\",\n" +
            "\t\t\"redirect_to_url\": {\n" +
            "\t\t\t\"url\": \"https://example.com/redirect\",\n" +
            "\t\t\t\"return_url\": \"yourapp://post-authentication-return-url\"\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    private static final String PARTIAL_PAYMENT_INTENT_WITH_AUTHORIZE_WITH_URL_JSON = "{\n" +
            "\t\"id\": \"pi_Aabcxyz01aDfoo\",\n" +
            "\t\"object\": \"payment_intent\",\n" +
            "\t\"status\": \"requires_action\",\n" +
            "\t\"next_action\": {\n" +
            "\t\t\"type\": \"redirect_to_url\",\n" +
            "\t\t\"redirect_to_url\": {\n" +
            "\t\t\t\"url\": \"https://example.com/redirect\",\n" +
            "\t\t\t\"return_url\": \"yourapp://post-authentication-return-url\"\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    @Test
    public void getAuthorizationUrl_whenProvidedBadUrl_doesNotCrash() {
        final PaymentIntent paymentIntent = PaymentIntent.fromString(
                PAYMENT_INTENT_WITH_SOURCE_WITH_BAD_AUTH_URL_JSON);
        assertNotNull(paymentIntent);

        final Uri authUrl = paymentIntent.getRedirectUrl();
        assertNotNull(authUrl);
        assertEquals(BAD_URL, authUrl.getEncodedPath());
    }

    @Test
    public void getRedirectUrl_withRedirectToUrlPopulate_returnsRedirectUrl() {
        final PaymentIntent paymentIntent = PaymentIntent
                .fromString(PARTIAL_PAYMENT_INTENT_WITH_REDIRECT_URL_JSON);
        assertNotNull(paymentIntent);
        assertTrue(paymentIntent.requiresAction());
        assertEquals(StripeIntent.NextActionType.RedirectToUrl, paymentIntent.getNextActionType());
        final Uri redirectUrl = paymentIntent.getRedirectUrl();
        assertNotNull(redirectUrl);
        assertEquals("https://example.com/redirect", redirectUrl.toString());
    }

    @Test
    public void getRedirectUrl_withAuthorizeWithUrlPopulated_returnsRedirectUrl() {
        final PaymentIntent paymentIntent = PaymentIntent
                .fromString(PARTIAL_PAYMENT_INTENT_WITH_AUTHORIZE_WITH_URL_JSON);
        assertNotNull(paymentIntent);
        assertEquals(StripeIntent.NextActionType.RedirectToUrl, paymentIntent.getNextActionType());
        final Uri redirectUrl = paymentIntent.getRedirectUrl();
        assertNotNull(redirectUrl);
        assertEquals("https://example.com/redirect", redirectUrl.toString());
    }

    @Test
    public void parseIdFromClientSecret_parsesCorrectly() {
        final String clientSecret = "pi_1CkiBMLENEVhOs7YMtUehLau_secret_s4O8SDh7s6spSmHDw1VaYPGZA";
        final String id = PaymentIntent.parseIdFromClientSecret(clientSecret);
        assertEquals("pi_1CkiBMLENEVhOs7YMtUehLau", id);
    }

    @Test
    public void parsePaymentIntentWithPaymentMethods() {
        final PaymentIntent paymentIntent =
                PaymentIntent.fromString(PAYMENT_INTENT_WITH_PAYMENT_METHODS_JSON);
        assertNotNull(paymentIntent);
        assertFalse(paymentIntent.requiresAction());
        assertEquals("card", paymentIntent.getPaymentMethodTypes().get(0));
    }

    @Test
    public void getNextActionTypeAndStripeSdkData_whenUseStripeSdkWith3ds2() {
        assertEquals(StripeIntent.NextActionType.UseStripeSdk,
                PaymentIntentFixtures.PI_REQUIRES_MASTERCARD_3DS2.getNextActionType());
        final PaymentIntent.SdkData sdkData =
                PaymentIntentFixtures.PI_REQUIRES_MASTERCARD_3DS2.getStripeSdkData();
        assertNotNull(sdkData);
        assertTrue(sdkData.is3ds2());
        assertEquals("mastercard", sdkData.data.get("directory_server_name"));
    }

    @Test
    public void getNextActionTypeAndStripeSdkData_whenUseStripeSdkWith3ds1() {
        final PaymentIntent paymentIntent = PaymentIntentFixtures.PI_REQUIRES_3DS1;
        assertEquals(StripeIntent.NextActionType.UseStripeSdk, paymentIntent.getNextActionType());
        final PaymentIntent.SdkData sdkData = paymentIntent.getStripeSdkData();
        assertNotNull(sdkData);
        assertTrue(sdkData.is3ds1());
        assertNotNull(sdkData.data.get("stripe_js"));
    }

    @Test
    public void getNextActionTypeAndStripeSdkData_whenRedirectToUrl() {
        assertEquals(StripeIntent.NextActionType.RedirectToUrl,
                PaymentIntentFixtures.PI_REQUIRES_REDIRECT.getNextActionType());
        assertNull(PaymentIntentFixtures.PI_REQUIRES_REDIRECT.getStripeSdkData());
    }
}
