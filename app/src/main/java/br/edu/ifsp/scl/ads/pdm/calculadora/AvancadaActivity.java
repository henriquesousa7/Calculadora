package br.edu.ifsp.scl.ads.pdm.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import br.edu.ifsp.scl.ads.pdm.calculadora.databinding.ActivityAvancadaBinding;

public class AvancadaActivity extends AppCompatActivity {
    private ActivityAvancadaBinding activityAvancadaBinding;

    private String expressao = "";
    private String formula = "";
    private String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityAvancadaBinding = activityAvancadaBinding.inflate(getLayoutInflater());
        setContentView(activityAvancadaBinding.getRoot());
    }

    private void setExpressao(String valor)
    {
        expressao = expressao + valor;
        activityAvancadaBinding.expressaoTv.setText(expressao);
    }


    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        if(expressao.contains("%")) {
            checkPorcent();
        } else if (expressao.contains("^")) {
            checkExpo();
        } else {
            checkRaiz();
        }

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Entrada invalida", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            activityAvancadaBinding.resultTv.setText(String.valueOf(result.doubleValue()));

        System.out.println(formula);
    }

    private void checkExpo()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < expressao.length(); i++)
        {
            if (expressao.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = expressao;
        tempFormula = expressao;
        for(Integer index: indexOfPowers)
        {
            mudaFormulaExpo(index);
        }
        formula = tempFormula;
    }

    private void mudaFormulaExpo(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< expressao.length(); i++)
        {
            if(isNumeric(expressao.charAt(i)))
                numberRight = numberRight + expressao.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(expressao.charAt(i)))
                numberLeft = numberLeft + expressao.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private void checkRaiz()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < expressao.length(); i++)
        {
            if (expressao.charAt(i) == '√')
                indexOfPowers.add(i);
        }

        formula = expressao;
        tempFormula = expressao;
        for(Integer index: indexOfPowers)
        {
            mudaFormulaRaiz(index);
        }
        formula = tempFormula;
    }

    private void mudaFormulaRaiz(Integer index)
    {
        String numberRight = "";

        for(int i = index + 1; i< expressao.length(); i++)
        {
            if(isNumeric(expressao.charAt(i)))
                numberRight = numberRight + expressao.charAt(i);
            else
                break;
        }

        String original = "√" + numberRight;
        String changed = "Math.sqrt("+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private void checkPorcent()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < expressao.length(); i++)
        {
            if (expressao.charAt(i) == '%')
                indexOfPowers.add(i);
        }

        formula = expressao;
        tempFormula = expressao;
        for(Integer index: indexOfPowers)
        {
            mudaFormulaPorcent(index);
        }
        formula = tempFormula;
    }

    private void mudaFormulaPorcent(Integer index)
    {
        String numberLeft = "";
        String numberLeftConverted = "";

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(expressao.charAt(i)))
                numberLeft = numberLeft + expressao.charAt(i);
            else
                break;
        }

        String original = numberLeftConverted + "%";
        String changed = "" +numberLeftConverted +"/100";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }

    public void limpaOnClick(View view)
    {
        activityAvancadaBinding.expressaoTv.setText("");
        expressao = "";
        activityAvancadaBinding.resultTv.setText("");
    }

    public void raizOnClick(View view)
    {
        setExpressao("√");
    }

    public void expoOnClick(View view)
    {
        setExpressao("^");
    }

    public void porcentOnClick(View view)
    {
        setExpressao("%");
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculadora, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calcAvancMi:
                Intent avancadaIntent = new Intent("AVANCADA_ACTION");
                startActivity(avancadaIntent);
                return true;

            case R.id.calcSimplesMi:
                Intent simplesIntent = new Intent(this, MainActivity.class);
                startActivity(simplesIntent);
                return true;
        }

        return false;
    }

}
