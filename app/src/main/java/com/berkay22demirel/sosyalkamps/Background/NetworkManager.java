package com.berkay22demirel.sosyalkamps.Background;

import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Arkadas;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Konum;
import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Mesaj;
import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.Yorum;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class NetworkManager {

    public static String baseURL = "http://10.0.2.2/sosyalkampus/";
    public static String profilKaydetURL = baseURL + "kullanici_kaydet.php";
    public static String girisYapURL = baseURL + "giris_yap.php";
    public static String profilGetirURL = baseURL + "profil_getir.php";
    public static String paylasimKaydetURL = baseURL + "paylasim_kaydet.php";
    public static String arkadasListesiGetirURL = baseURL + "arkadas_listesi_getir.php";
    public static String kisiListesiGetirURL = baseURL + "kisi_listesi_getir.php";
    public static String arkadasKaydetURL = baseURL + "arkadas_ekle.php";
    public static String arkadasSilURL = baseURL + "arkadas_sil.php";
    public static String profilPaylasimGetirURL = baseURL + "profil_paylasim_getir.php";
    public static String zamanTuneliPaylasimGetirURL = baseURL + "zaman_tuneli_paylasim_getir.php";
    public static String mesajGonderURL = baseURL + "mesaj_gonder.php";
    public static String mesajGetirURL = baseURL + "mesaj_getir.php";
    public static String isletmeciGirisYapURL = baseURL + "isletmeci_giris_yap.php";
    public static String isletmeciKaydetURL = baseURL + "isletmeci_kaydet.php";
    public static String etkinlikVeMekanGetirURL = baseURL + "etkinlik_ve_mekan_getir.php";
    public static String yorumGetirURL = baseURL + "yorum_getir.php";
    public static String yorumKaydetURL = baseURL + "yorum_kaydet.php";
    public static String profilDuzenleURL = baseURL + "kullanici_duzenle.php";
    public static String isletmeciGetirURL = baseURL + "isletmeci_getir.php";

    public static String kullaniciKaydet(Kullanici kullanici){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(profilKaydetURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",kullanici.getEposta()));
            parametreList.add(new BasicNameValuePair("sifre",kullanici.getSifre()));
            parametreList.add(new BasicNameValuePair("ad",kullanici.getAd()));
            parametreList.add(new BasicNameValuePair("soyad",kullanici.getSoyad()));
            parametreList.add(new BasicNameValuePair("ceptelefonu",kullanici.getCeptelefonu()));
            parametreList.add(new BasicNameValuePair("dogumtarihi","05 10 1995"));
            parametreList.add(new BasicNameValuePair("cinsiyet",kullanici.getCinsiyet()));
            parametreList.add(new BasicNameValuePair("fotografurl","Fotograf Url"));
            parametreList.add(new BasicNameValuePair("bio","Bio boş"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Kullanici kaydedilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String girisYap(Kullanici kullanici){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(girisYapURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",kullanici.getEposta()));
            parametreList.add(new BasicNameValuePair("sifre",kullanici.getSifre()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Giriş yapılırken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Kullanici profilGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(profilGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            Kullanici gelenKullanici = getProfilGetirInputStream(content);
            return gelenKullanici;
        }catch (Exception e){
            Log.d("Network Manager","Profil getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Kullanici getProfilGetirInputStream(InputStream content){
        Kullanici gelenKullanici = new Kullanici();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList kullaniciNodeList = root.getElementsByTagName("profil");
            if(kullaniciNodeList == null || kullaniciNodeList.getLength() == 0){
                return gelenKullanici;
            }

            Element profil = (Element) kullaniciNodeList.item(0);

            Node epostaNode = profil.getElementsByTagName("eposta").item(0);
            String eposta = epostaNode.getFirstChild().getNodeValue();

            Node sifreNode = profil.getElementsByTagName("sifre").item(0).getFirstChild();
            String sifre = sifreNode.getNodeValue();

            Node adNode = profil.getElementsByTagName("ad").item(0).getFirstChild();
            String ad = adNode.getNodeValue();

            Node soyadNode = profil.getElementsByTagName("soyad").item(0).getFirstChild();
            String soyad = soyadNode.getNodeValue();

            Node telefonNode = profil.getElementsByTagName("ceptelefonu").item(0).getFirstChild();
            String telefon = telefonNode.getNodeValue();

            Node dogumTarihiNode = profil.getElementsByTagName("dogumtarihi").item(0).getFirstChild();
            String dogumTarihi = (dogumTarihiNode != null) ? dogumTarihiNode.getNodeValue() : "";

            Node cinsiyetNode = profil.getElementsByTagName("cinsiyet").item(0).getFirstChild();
            String cinsiyet = cinsiyetNode.getNodeValue();

            Node fotografUrlNode = profil.getElementsByTagName("fotografurl").item(0).getFirstChild();
            String fotografUrl = (fotografUrlNode != null) ? fotografUrlNode.getNodeValue() : "";

            Node bioNode = profil.getElementsByTagName("bio").item(0).getFirstChild();
            String bio = (bioNode != null) ? bioNode.getNodeValue() : "";

            gelenKullanici.setEposta(eposta);
            gelenKullanici.setSifre(sifre);
            gelenKullanici.setAd(ad);
            gelenKullanici.setSoyad(soyad);
            gelenKullanici.setCeptelefonu(telefon);
            gelenKullanici.setDogumtarihi(dogumTarihi);
            gelenKullanici.setCinsiyet(cinsiyet);
            gelenKullanici.setFotografUrl(fotografUrl);
            gelenKullanici.setBio(bio);

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return gelenKullanici;
    }

    public static String paylasimKaydet(Paylasim paylasim){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(paylasimKaydetURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("kullanicieposta",paylasim.getEposta()));
            parametreList.add(new BasicNameValuePair("icerik",paylasim.getYazi()));
            parametreList.add(new BasicNameValuePair("resimurl",paylasim.getFotografurl()));
            parametreList.add(new BasicNameValuePair("paylasimzamani",paylasim.getFotografurl()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Paylasim kaydedilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Kullanici> arkadasListesiGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(arkadasListesiGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Kullanici> arkadasListesi = getArkadasListesiGetirInputStream(content);
            return arkadasListesi;
        }catch (Exception e){
            Log.d("Network Manager","Profil getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Kullanici> getArkadasListesiGetirInputStream(InputStream content){

        List<Kullanici> arkadasListesi = new ArrayList<Kullanici>();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList kullaniciNodeList = root.getElementsByTagName("profil");
            if(kullaniciNodeList == null || kullaniciNodeList.getLength() == 0){
                return arkadasListesi;
            }

            int arkadasSayisi = kullaniciNodeList.getLength();

            for (int i = 0; i < arkadasSayisi; i++) {

                Element profil = (Element) kullaniciNodeList.item(i);

                Node epostaNode = profil.getElementsByTagName("eposta").item(0);
                String eposta = epostaNode.getFirstChild().getNodeValue();

                Node sifreNode = profil.getElementsByTagName("sifre").item(0).getFirstChild();
                String sifre = sifreNode.getNodeValue();

                Node adNode = profil.getElementsByTagName("ad").item(0).getFirstChild();
                String ad = adNode.getNodeValue();

                Node soyadNode = profil.getElementsByTagName("soyad").item(0).getFirstChild();
                String soyad = soyadNode.getNodeValue();

                Node telefonNode = profil.getElementsByTagName("ceptelefonu").item(0).getFirstChild();
                String telefon = telefonNode.getNodeValue();

                Node dogumTarihiNode = profil.getElementsByTagName("dogumtarihi").item(0).getFirstChild();
                String dogumTarihi = (dogumTarihiNode != null) ? dogumTarihiNode.getNodeValue() : "";

                Node cinsiyetNode = profil.getElementsByTagName("cinsiyet").item(0).getFirstChild();
                String cinsiyet = cinsiyetNode.getNodeValue();

                Node fotografUrlNode = profil.getElementsByTagName("fotografurl").item(0).getFirstChild();
                String fotografUrl = (fotografUrlNode != null) ? fotografUrlNode.getNodeValue() : "";

                Node bioNode = profil.getElementsByTagName("bio").item(0).getFirstChild();
                String bio = (bioNode != null) ? bioNode.getNodeValue() : "";

                Kullanici gelenKullanici = new Kullanici();

                gelenKullanici.setEposta(eposta);
                gelenKullanici.setSifre(sifre);
                gelenKullanici.setAd(ad);
                gelenKullanici.setSoyad(soyad);
                gelenKullanici.setCeptelefonu(telefon);
                gelenKullanici.setDogumtarihi(dogumTarihi);
                gelenKullanici.setCinsiyet(cinsiyet);
                gelenKullanici.setFotografUrl(fotografUrl);
                gelenKullanici.setBio(bio);

                arkadasListesi.add(gelenKullanici);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return arkadasListesi;
    }

    public static List<Kullanici> kisiListesiGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(kisiListesiGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Kullanici> kisiListesi = getKisiListesiGetirInputStream(content);
            return kisiListesi;
        }catch (Exception e){
            Log.d("Network Manager","Profil getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Kullanici> getKisiListesiGetirInputStream(InputStream content){

        List<Kullanici> kisiListesi = new ArrayList<Kullanici>();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList kullaniciNodeList = root.getElementsByTagName("profil");
            if(kullaniciNodeList == null || kullaniciNodeList.getLength() == 0){
                return kisiListesi;
            }

            int kisiSayisi = kullaniciNodeList.getLength();

            for (int i = 0; i < kisiSayisi; i++) {

                Element profil = (Element) kullaniciNodeList.item(i);

                Node epostaNode = profil.getElementsByTagName("eposta").item(0);
                String eposta = epostaNode.getFirstChild().getNodeValue();

                Node sifreNode = profil.getElementsByTagName("sifre").item(0).getFirstChild();
                String sifre = sifreNode.getNodeValue();

                Node adNode = profil.getElementsByTagName("ad").item(0).getFirstChild();
                String ad = adNode.getNodeValue();

                Node soyadNode = profil.getElementsByTagName("soyad").item(0).getFirstChild();
                String soyad = soyadNode.getNodeValue();

                Node telefonNode = profil.getElementsByTagName("ceptelefonu").item(0).getFirstChild();
                String telefon = telefonNode.getNodeValue();

                Node dogumTarihiNode = profil.getElementsByTagName("dogumtarihi").item(0).getFirstChild();
                String dogumTarihi = (dogumTarihiNode != null) ? dogumTarihiNode.getNodeValue() : "";

                Node cinsiyetNode = profil.getElementsByTagName("cinsiyet").item(0).getFirstChild();
                String cinsiyet = cinsiyetNode.getNodeValue();

                Node fotografUrlNode = profil.getElementsByTagName("fotografurl").item(0).getFirstChild();
                String fotografUrl = (fotografUrlNode != null) ? fotografUrlNode.getNodeValue() : "";

                Node bioNode = profil.getElementsByTagName("bio").item(0).getFirstChild();
                String bio = (bioNode != null) ? bioNode.getNodeValue() : "";

                Kullanici gelenKullanici = new Kullanici();

                gelenKullanici.setEposta(eposta);
                gelenKullanici.setSifre(sifre);
                gelenKullanici.setAd(ad);
                gelenKullanici.setSoyad(soyad);
                gelenKullanici.setCeptelefonu(telefon);
                gelenKullanici.setDogumtarihi(dogumTarihi);
                gelenKullanici.setCinsiyet(cinsiyet);
                gelenKullanici.setFotografUrl(fotografUrl);
                gelenKullanici.setBio(bio);

                kisiListesi.add(gelenKullanici);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return kisiListesi;
    }

    public static String arkadasKaydet(Arkadas arkadas){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(arkadasKaydetURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",arkadas.getEposta()));
            parametreList.add(new BasicNameValuePair("arkadaseposta",arkadas.getArkadasEposta()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Arkadaş eklenirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String arkadasSil(Arkadas arkadas){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(arkadasSilURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",arkadas.getEposta()));
            parametreList.add(new BasicNameValuePair("arkadaseposta",arkadas.getArkadasEposta()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Arkadaş silinirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Paylasim> profilPaylasimGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(profilPaylasimGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Paylasim> paylasimListesi = getProfilPaylasimGetirInputStream(content);
            return paylasimListesi;
        }catch (Exception e){
            Log.d("Network Manager","Paylaşımlar getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Paylasim> getProfilPaylasimGetirInputStream(InputStream content){
        List<Paylasim> paylasimListesi = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList paylasimNodeList = root.getElementsByTagName("paylasim");
            if(paylasimNodeList == null || paylasimNodeList.getLength() == 0){
                return paylasimListesi;
            }

            int paylasimSayisi = paylasimNodeList.getLength();

            for (int i = 0; i < paylasimSayisi; i++) {

                Element paylasim = (Element) paylasimNodeList.item(i);

                Node epostaNode = paylasim.getElementsByTagName("kullanicieposta").item(0);
                String eposta = epostaNode.getFirstChild().getNodeValue();

                Node icerikNode = paylasim.getElementsByTagName("icerik").item(0).getFirstChild();
                String icerik = icerikNode.getNodeValue();

                Node resimurlNode = paylasim.getElementsByTagName("resimurl").item(0).getFirstChild();
                String resimurl = resimurlNode.getNodeValue();

                Node paylasimzamaniNode = paylasim.getElementsByTagName("paylasimzamani").item(0).getFirstChild();
                String paylasimzamani = paylasimzamaniNode.getNodeValue();

                Paylasim paylasim1 = new Paylasim();
                paylasim1.setEposta(eposta);
                paylasim1.setYazi(icerik);
                paylasim1.setFotografurl(resimurl);
                paylasim1.setPaylasimZamani(paylasimzamani);

                paylasimListesi.add(paylasim1);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return paylasimListesi;
    }

    public static List<Paylasim> zamanTuneliPaylasimGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(zamanTuneliPaylasimGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Paylasim> paylasimListesi = getZamanTuneliPaylasimGetirInputStream(content);
            return paylasimListesi;
        }catch (Exception e){
            Log.d("Network Manager","Paylaşımlar getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Paylasim> getZamanTuneliPaylasimGetirInputStream(InputStream content){
        List<Paylasim> paylasimListesi = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList paylasimNodeList = root.getElementsByTagName("paylasim2");
            if(paylasimNodeList == null || paylasimNodeList.getLength() == 0){
                return paylasimListesi;
            }

            int paylasimSayisi = paylasimNodeList.getLength();

            for (int i = 0; i < paylasimSayisi; i++) {

                Element paylasim = (Element) paylasimNodeList.item(i);

                Node epostaNode = paylasim.getElementsByTagName("kullanicieposta").item(0);
                String eposta = epostaNode.getFirstChild().getNodeValue();

                Node icerikNode = paylasim.getElementsByTagName("icerik").item(0).getFirstChild();
                String icerik = icerikNode.getNodeValue();

                Node resimurlNode = paylasim.getElementsByTagName("resimurl").item(0).getFirstChild();
                String resimurl = resimurlNode.getNodeValue();

                Node paylasimzamaniNode = paylasim.getElementsByTagName("paylasimzamani").item(0).getFirstChild();
                String paylasimzamani = paylasimzamaniNode.getNodeValue();

                Paylasim paylasim1 = new Paylasim();
                paylasim1.setEposta(eposta);
                paylasim1.setYazi(icerik);
                paylasim1.setFotografurl(resimurl);
                paylasim1.setPaylasimZamani(paylasimzamani);

                paylasimListesi.add(paylasim1);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return paylasimListesi;
    }

    public static String mesajGonder(Mesaj mesaj){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(mesajGonderURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("kimdeneposta",mesaj.getKimdenEposta()));
            parametreList.add(new BasicNameValuePair("kimeeposta",mesaj.getKimeEposta()));
            parametreList.add(new BasicNameValuePair("mesaj",mesaj.getIcerik()));
            parametreList.add(new BasicNameValuePair("gonderimzamani","10.06.2018"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Mesaj gönderilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Mesaj> mesajGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(mesajGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("kimeeposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Mesaj> mesajlar = getMesajGetirInputStream(content);
            return mesajlar;
        }catch (Exception e){
            Log.d("Network Manager","Profil getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Mesaj> getMesajGetirInputStream(InputStream content){

        List<Mesaj> mesajlarListesi = new ArrayList<Mesaj>();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList mesajNodeList = root.getElementsByTagName("mesajlar");
            if(mesajNodeList == null || mesajNodeList.getLength() == 0){
                return mesajlarListesi;
            }

            int mesajSayisi = mesajNodeList.getLength();

            for (int i = 0; i < mesajSayisi; i++) {

                Element mesajlar = (Element) mesajNodeList.item(i);

                Node kimdenepostaNode = mesajlar.getElementsByTagName("kimdeneposta").item(0);
                String kimdeneposta = kimdenepostaNode.getFirstChild().getNodeValue();

                Node kimeepostaNode = mesajlar.getElementsByTagName("kimeeposta").item(0).getFirstChild();
                String kimeeposta = kimeepostaNode.getNodeValue();

                Node mesajNode = mesajlar.getElementsByTagName("mesaj").item(0).getFirstChild();
                String mesaj = mesajNode.getNodeValue();

                Node gonderimzamaniNode = mesajlar.getElementsByTagName("gonderimzamani").item(0).getFirstChild();
                String gonderimzamani = gonderimzamaniNode.getNodeValue();

                Mesaj gelenMesaj = new Mesaj();

                gelenMesaj.setKimeEposta(kimeeposta);
                gelenMesaj.setKimdenEposta(kimdeneposta);
                gelenMesaj.setIcerik(mesaj);
                gelenMesaj.setGönderimZamani(gonderimzamani);

                mesajlarListesi.add(gelenMesaj);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return mesajlarListesi;
    }

    public static String isletmeciGirisYap(Isletmeci isletmeci){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(isletmeciGirisYapURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",isletmeci.getEposta()));
            parametreList.add(new BasicNameValuePair("sifre",isletmeci.getSifre()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Giriş yapılırken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String isletmeciKaydet(Isletmeci isletmeci){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(isletmeciKaydetURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",isletmeci.getEposta()));
            parametreList.add(new BasicNameValuePair("sifre",isletmeci.getSifre()));
            parametreList.add(new BasicNameValuePair("mekanadi",isletmeci.getMekanAdi()));
            parametreList.add(new BasicNameValuePair("mekantelefon",isletmeci.getMekanTelefon()));
            parametreList.add(new BasicNameValuePair("mekanadres",isletmeci.getMekanAdres()));
            parametreList.add(new BasicNameValuePair("mekanfotografurl","Fotograf URL"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Kullanici kaydedilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static List<Isletmeci> etkinlikVeMekanGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(etkinlikVeMekanGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Isletmeci> paylasimListesi = getEtkinlikVeMekanGetirInputStream(content);
            return paylasimListesi;
        }catch (Exception e){
            Log.d("Network Manager","Etkinlik ve mekanlar getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Isletmeci> getEtkinlikVeMekanGetirInputStream(InputStream content){
        List<Isletmeci> etkinlikVeMekanListesi = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList etkinlikVeMekanNodeList = root.getElementsByTagName("etkinlikVeMekan");
            if(etkinlikVeMekanNodeList == null || etkinlikVeMekanNodeList.getLength() == 0){
                return etkinlikVeMekanListesi;
            }

            int etkinlikVeMekanSayisi = etkinlikVeMekanNodeList.getLength();

            for (int i = 0; i < etkinlikVeMekanSayisi; i++) {

                Element etkinlikVeMekan = (Element) etkinlikVeMekanNodeList.item(i);

                Node epostaNode = etkinlikVeMekan.getElementsByTagName("eposta").item(0);
                String eposta = epostaNode.getFirstChild().getNodeValue();

                Node icerikNode = etkinlikVeMekan.getElementsByTagName("mekanadi").item(0).getFirstChild();
                String mekanadi = icerikNode.getNodeValue();

                Node resimurlNode = etkinlikVeMekan.getElementsByTagName("mekantelefon").item(0).getFirstChild();
                String mekantelefon = resimurlNode.getNodeValue();

                Node paylasimzamaniNode = etkinlikVeMekan.getElementsByTagName("mekanadres").item(0).getFirstChild();
                String mekanadres = paylasimzamaniNode.getNodeValue();

                Node mekanfotografurlNode = etkinlikVeMekan.getElementsByTagName("mekanfotografurl").item(0).getFirstChild();
                String mekanfotografurl = mekanfotografurlNode.getNodeValue();

                Isletmeci isletmeci = new Isletmeci();
                isletmeci.setEposta(eposta);
                isletmeci.setMekanAdi(mekanadi);
                isletmeci.setMekanAdres(mekanadres);
                isletmeci.setMekanTelefon(mekantelefon);
                isletmeci.setMekanFotografUrl(mekanfotografurl);

                etkinlikVeMekanListesi.add(isletmeci);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return etkinlikVeMekanListesi;
    }

    public static List<Yorum> yorumGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(yorumGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("mekaneposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            List<Yorum> yorumlar = getYorumGetirInputStream(content);
            return yorumlar;
        }catch (Exception e){
            Log.d("Network Manager","Yorumlar getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static List<Yorum> getYorumGetirInputStream(InputStream content){

        List<Yorum> yorumlarListesi = new ArrayList<Yorum>();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList yorumNodeList = root.getElementsByTagName("yorumlar");
            if(yorumNodeList == null || yorumNodeList.getLength() == 0){
                return yorumlarListesi;
            }

            int yorumSayisi = yorumNodeList.getLength();

            for (int i = 0; i < yorumSayisi; i++) {

                Element mesajlar = (Element) yorumNodeList.item(i);

                Node mekanepostaNode = mesajlar.getElementsByTagName("mekaneposta").item(0);
                String mekaneposta = mekanepostaNode.getFirstChild().getNodeValue();

                Node yapilanyorumNode = mesajlar.getElementsByTagName("yapilanyorum").item(0).getFirstChild();
                String yapilanyorum = yapilanyorumNode.getNodeValue();

                Node yorumyapanepostaNode = mesajlar.getElementsByTagName("yorumyapaneposta").item(0).getFirstChild();
                String yorumyapaneposta = yorumyapanepostaNode.getNodeValue();

                Node paylasimzamaniNode = mesajlar.getElementsByTagName("paylasimzamani").item(0).getFirstChild();
                String paylasimzamani = paylasimzamaniNode.getNodeValue();

                Yorum gelenYorum = new Yorum();

                gelenYorum.setMekanEposta(mekaneposta);
                gelenYorum.setYorumYapanEposta(yorumyapaneposta);
                gelenYorum.setPaylasimZamani(paylasimzamani);
                gelenYorum.setYapilanYorum(yapilanyorum);

                yorumlarListesi.add(gelenYorum);

            }

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return yorumlarListesi;
    }

    public static String yorumKaydet(Yorum yorum){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(yorumKaydetURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("mekaneposta",yorum.getMekanEposta()));
            parametreList.add(new BasicNameValuePair("yapilanyorum",yorum.getYapilanYorum()));
            parametreList.add(new BasicNameValuePair("yorumyapaneposta",yorum.getYorumYapanEposta()));
            parametreList.add(new BasicNameValuePair("paylasimzamani",yorum.getPaylasimZamani()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Yorum kaydedilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String kullaniciDuzenle(Kullanici kullanici){
        BufferedReader in = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(profilDuzenleURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",kullanici.getEposta()));
            parametreList.add(new BasicNameValuePair("sifre",kullanici.getSifre()));
            parametreList.add(new BasicNameValuePair("ad",kullanici.getAd()));
            parametreList.add(new BasicNameValuePair("soyad",kullanici.getSoyad()));
            parametreList.add(new BasicNameValuePair("ceptelefonu",kullanici.getCeptelefonu()));
            parametreList.add(new BasicNameValuePair("dogumtarihi","05 10 1995"));
            parametreList.add(new BasicNameValuePair("cinsiyet",kullanici.getCinsiyet()));
            parametreList.add(new BasicNameValuePair("fotografurl","Fotograf Url"));
            parametreList.add(new BasicNameValuePair("bio",kullanici.getBio()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        }catch (Exception e){
            Log.d("Network Manager","Kullanici kaydedilirken bir hata oluştu!!!",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Isletmeci isletmeciGetir(String eposta){
        InputStream content = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(isletmeciGetirURL);
            List<NameValuePair> parametreList = new ArrayList<NameValuePair>();
            parametreList.add(new BasicNameValuePair("eposta",eposta));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametreList);
            request.setEntity(entity);
            HttpResponse  response = client.execute(request);
            content = response.getEntity().getContent();
            Isletmeci gelenIsletmeci = getIsletmeciGetirInputStream(content);
            return gelenIsletmeci;
        }catch (Exception e){
            Log.d("Network Manager","İşletmeci getirilirken bir hata oluştu!!!",e);
        }finally {
            if(content != null){
                try {
                    content.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Isletmeci getIsletmeciGetirInputStream(InputStream content){
        Isletmeci gelenIsletmeci = new Isletmeci();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(content);
            Element root = document.getDocumentElement();
            NodeList isletmeciNodeList = root.getElementsByTagName("isletmeci");
            if(isletmeciNodeList == null || isletmeciNodeList.getLength() == 0){
                return gelenIsletmeci;
            }

            Element profil = (Element) isletmeciNodeList.item(0);

            Node epostaNode = profil.getElementsByTagName("eposta").item(0);
            String eposta = epostaNode.getFirstChild().getNodeValue();

            Node sifreNode = profil.getElementsByTagName("sifre").item(0).getFirstChild();
            String sifre = sifreNode.getNodeValue();

            Node mekanadiNode = profil.getElementsByTagName("mekanadi").item(0).getFirstChild();
            String mekanadi = mekanadiNode.getNodeValue();

            Node mekantelefonNode = profil.getElementsByTagName("mekantelefon").item(0).getFirstChild();
            String mekantelefon = mekantelefonNode.getNodeValue();

            Node mekanadresNode = profil.getElementsByTagName("mekanadres").item(0).getFirstChild();
            String mekanadres = mekanadresNode.getNodeValue();

            Node mekanfotografurlNode = profil.getElementsByTagName("mekanfotografurl").item(0).getFirstChild();
            String mekanfotografurl = (mekanfotografurlNode != null) ? mekanfotografurlNode.getNodeValue() : "";

            gelenIsletmeci.setEposta(eposta);
            gelenIsletmeci.setSifre(sifre);
            gelenIsletmeci.setMekanAdres(mekanadres);
            gelenIsletmeci.setMekanFotografUrl(mekanfotografurl);
            gelenIsletmeci.setMekanAdi(mekanadi);
            gelenIsletmeci.setMekanTelefon(mekantelefon);

        } catch (Exception e) {
            Log.d("Network Manager", "XML parse edilirken hata olustu", e);
        }
        return gelenIsletmeci;
    }
}
