package com.essa.curso.jetpack.architecturecomponents.ui.architecturecomp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.essa.curso.jetpack.architecturecomponents.R
import com.essa.curso.jetpack.architecturecomponents.ui.adapter.CompanieroAdapter
import com.essa.curso.jetpack.architecturecomponents.utils.InjectorUtils
import kotlinx.android.synthetic.main.architecture_comp_fragment.*

class ArchitectureCompFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: CompanieroAdapter

    companion object {
        fun newInstance() = ArchitectureCompFragment()
    }

    //VARIABLE DE ENLACE CON EL VIEW MODEL
    private lateinit var viewModel: ArchitectureCompViewModel

    //SE CREA LA VISTA O MEJOR DICHO EL FRAGMENT EN ESTE CASO
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //SE ENLAZA ESTE FRAGMENT CON SU LAYOUT CORRESPONDIENTE, EL CONTAINER SOLO DICE SI EL FRAGMENT
        //DEBE SER AÑADIDO A ALGUN VIEWGROUP COMO POR EJEMPLO UN CONSTRAINLAYOUT, Y ATTACHTOROOT EN FALSO
        //SOLO LE INDICA QUE AUN NO LO AGREGE AL CONTENEDOR, ES DECIR SIEMPRE SE VA A AGREGAR PERO
        //PORNERLO EN FALSE LO QUE DICE ES AGREGALO PERO CON RETASO
        val view = inflater.inflate(R.layout.architecture_comp_fragment, container, false)
        //SE OBTIENE LA REFERENCIA HACIA EL ID DEL RECYCLER VIEW DEL FRAGMENT
        mRecyclerView = view.findViewById(R.id.architecture_comp_fragment_recyclerview)
        //SE LLAMA AL METODO QUE LO INICIALIZA AL RECYCLERVIEW
        initRecyclerView()
        return view 

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //OBTENEMOS LA FACTORIA
        val factory = InjectorUtils.inyectarArchConViewModelFactory(this.context!!);
        //CON EL THIS LE DECIMOS AL VIEW MODEL QUE DEBE ESTAR PENDiENTE DEL CICLO DE VIDA DE ESTE
        //CONTEXTO ES DECIR DEL FRAGMENT Y LE DECIMOS QUE DEBE CREAR UN ArchitectureCompViewModel.java
        viewModel = ViewModelProvider(this, factory).get(ArchitectureCompViewModel::class.java);

        //POR OTRA PARTE CADA VEZ QUE OCURRAN CAMBIOS EN LA LISTA DE COMPAÑEROS ESTAMOS ATENTOS
        //A SUS CAMBIOS
        suscribirnosCambios()
    }

    private fun suscribirnosCambios(){
        //CADA VEZ QUE AYA UN CAMBIO EN LA LISTA DE COMPAÑEROS ESTA LO ESTARA OBSERVANDO
        //MEDIANTE EL LISTENER OBSERVABLE QUE SE LE ESTA ASICNANDO
        viewModel.mListaCompanieros.observe( this.activity!! , Observer { value ->
            //ESTO LO QUE HACE ES RECIBIR LA LISTA CON LOS CAMBIOS
            mAdapter = CompanieroAdapter(value)
            //POR ULTIMO SE SETEA EL ADAPTER DEL RECYCLEVIEW
            mRecyclerView.adapter = mAdapter
        })
    }

    private fun initRecyclerView(){
        //SE LE DICE AL LINEAR LAYOUT QUE EL MANEJADOR DE EL VA A SER ESTA ACTIVIDAD
        mLinearLayoutManager = LinearLayoutManager(this.activity)
        //Y AL RECYCLER VIE SE LE DICE CUAL VA A SER SU MANEJADOR
        mRecyclerView.layoutManager = mLinearLayoutManager
    }

}
