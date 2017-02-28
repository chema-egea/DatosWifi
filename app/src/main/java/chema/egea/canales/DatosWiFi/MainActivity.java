package chema.egea.canales.DatosWiFi;

import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarDatos();
    }

    public void mostrarDatos()
    {
        TextView textInfo = (TextView)findViewById(R.id.datoswifi);

        WifiManager wifiManager=(WifiManager)getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo=wifiManager.getConnectionInfo();
        DhcpInfo dhcp = wifiManager.getDhcpInfo();

        if (wifiInfo.getBSSID()!=null)
        {
            String ssid = wifiInfo.getSSID();
            String bssid = wifiInfo.getBSSID();
            String frecuencia = ""+wifiInfo.getFrequency();
            int ipadress = wifiInfo.getIpAddress();
            String ip = String.format(
                    "%d.%d.%d.%d",
                    (ipadress & 0xff),
                    (ipadress >> 8 & 0xff),
                    (ipadress >> 16 & 0xff),
                    (ipadress >> 24 & 0xff));
            String ssid_oculta = ""+wifiInfo.getHiddenSSID();
            String velocidad = ""+wifiInfo.getLinkSpeed()+ " Mbps";
            String mac = wifiInfo.getMacAddress();
            String idRed = ""+wifiInfo.getNetworkId();
            String fuerzaSignal = ""+wifiInfo.getRssi()+" dBm";

            String puerta = String.format(
                    "%d.%d.%d.%d",
                    (dhcp.gateway & 0xff),
                    (dhcp.gateway >> 8 & 0xff),
                    (dhcp.gateway >> 16 & 0xff),
                    (dhcp.gateway >> 24 & 0xff));
            String mascara = String.format(
                    "%d.%d.%d.%d",
                    (dhcp.netmask & 0xff),
                    (dhcp.netmask >> 8 & 0xff),
                    (dhcp.netmask >> 16 & 0xff),
                    (dhcp.netmask >> 24 & 0xff));

            String informacion = String.format("Datos de la red wifi:\n\nSSID: %s\nBSSID: %s\nFrecuencia: %s\nIP privada: %s\nSSID Oculta: %s\nVelocidad: %s\nDirección MAC: %s\nID de red: %s\nFuerza de la señal: %s\nPuerta de enlace: %s\nMáscara: %s\n", ssid, bssid, frecuencia, ip, ssid_oculta, velocidad, mac, idRed, fuerzaSignal, puerta, mascara);

            textInfo.setText(informacion);

        }
        else
        {
            textInfo.setText("No se pudieron mostrar los datos.");
        }

    }
}
