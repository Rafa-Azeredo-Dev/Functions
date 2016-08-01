package br.com.sistemaathos.functions;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.graphics.drawable.DrawableCompat;

import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * @author Rafael de Azeredo
 * @since 1.0.0
 * @version 1.0.0
 */
public class Functions {

    private static final String BARCODE_ZXING_SCANNER = "com.google.zxing.client.android.SCAN";

    public static boolean isIntentAvailable(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(BARCODE_ZXING_SCANNER);
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkLocationPermission(Context context) {
        return context.checkSelfPermission(ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && context.checkSelfPermission(ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED;
    }

    public static Intent getLocationSourceIntent() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    public static Drawable getTintedDrawable(Drawable d, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(d);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    public static void killApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * Senha do Dia do Athos
     * @return Retorna a senha do dia
     */
    public static String getSenhaDia() {

        Calendar d1 = Calendar.getInstance();
        d1.set(Calendar.DAY_OF_MONTH, 30);
        d1.set(Calendar.MONTH, 11);
        d1.set(Calendar.YEAR, 1899);

        Calendar today = Calendar.getInstance();

        long days = ((today.getTimeInMillis() - d1.getTimeInMillis()) / (24 * 60 * 60 * 1000)) * 17;

        String sSenha, sFinal;

        sSenha = String.valueOf(days);

        switch ((today.get(Calendar.DAY_OF_WEEK) - 1)) {
            case 0: // domingo
                sFinal = "J";
                break;
            case 1: // segunda
                sFinal = "K";
                break;
            case 2: // terca
                sFinal = "1";
                break;
            case 3: // quarta
                sFinal = "B";
                break;
            case 4: // quinta
                sFinal = "9";
                break;
            case 5: // sexta
                sFinal = "C";
                break;
            case 6: // sabado
                sFinal = "5";
                break;
            default: // nada
                sFinal = "8";
        }

        sSenha = sFinal + sSenha.substring(sSenha.length() - 4, 5);

        return sSenha;
    }

    public static int compareBooleanDefaultTrue(final String value) {
        if(value == null) return 1;
        return value.equals("t") || value.equals("true") ? 1 : 0;
    }

    public static int compareBooleanDefaultFalse(final String value) {
        if(value == null) return 0;
        return value.equals("t") || value.equals("true") ? 1 : 0;
    }

    public static int key() {
        return 5;
    }

    public static String cipher(String mensagem, int chave) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char c = (char) (mensagem.charAt(i) + chave);
            builder.append(c);
        }

        return builder.toString();
    }

    public static String encrypt(String mensagem, int chave) {
        return cipher(mensagem, +chave);
    }

    public static String decrypt(String mensagem, int chave) {
        return cipher(mensagem, -chave);
    }
}
