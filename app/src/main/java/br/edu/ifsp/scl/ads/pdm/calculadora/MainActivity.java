package br.edu.ifsp.scl.ads.pdm.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import br.edu.ifsp.scl.ads.pdm.calculadora.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private String expressao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    private void setExpressao(String valor)
    {
        expressao = expressao + valor;
        activityMainBinding.expressaoTv.setText(expressao);
    }


    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double)engine.eval(expressao);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Entrada invalida", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            activityMainBinding.resultTv.setText(String.valueOf(result.doubleValue()));
    }

    public void limpaOnClick(View view)
    {
        activityMainBinding.expressaoTv.setText("");
        expressao = "";
        activityMainBinding.resultTv.setText("");
    }

    public void divisaoOnClick(View view)
    {
        setExpressao("/");
    }

    public void seteOnClick(View view)
    {
        setExpressao("7");
    }

    public void oitoOnClick(View view)
    {
        setExpressao("8");
    }

    public void noveOnClick(View view)
    {
        setExpressao("9");
    }

    public void multOnClick(View view)
    {
        setExpressao("*");
    }

    public void quatroOnClick(View view)
    {
        setExpressao("4");
    }

    public void cincoOnClick(View view)
    {
        setExpressao("5");
    }

    public void seisOnClick(View view)
    {
        setExpressao("6");
    }

    public void subOnClick(View view)
    {
        setExpressao("-");
    }

    public void umOnClick(View view)
    {
        setExpressao("1");
    }

    public void doisOnClick(View view)
    {
        setExpressao("2");
    }

    public void tresOnClick(View view)
    {
        setExpressao("3");
    }

    public void adicaoOnClick(View view)
    {
        setExpressao("+");
    }

    public void decimalOnClick(View view)
    {
        setExpressao(".");
    }

    public void zeroOnClick(View view)
    {
        setExpressao("0");
    }

}