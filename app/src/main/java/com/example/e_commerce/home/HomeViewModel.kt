package com.example.e_commerce.home

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.subCategories.SubCategoryItem
import com.example.domain.contract.repository.SignUpRepository
import com.example.domain.contract.repository.SubCategoriesRepository
import com.example.domain.model.categories.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SignUpRepository ,
                                        private val subCategoriesRepository: SubCategoriesRepository):ViewModel() {


 var categoriesList = mutableStateListOf<CategoryItem?>()

 var selectedSubCategoriesList = mutableStateListOf<SubCategoryItem?>()

 var selectedCategoryIndex = mutableIntStateOf(0)


 fun getSubCategories(id: String?){

     viewModelScope.launch {

        if (id!=null){
            val response =  subCategoriesRepository.getAllSubCategoriesOnCategory(id)

            selectedSubCategoriesList.clear()

            if (response?.isNotEmpty()==true){

                selectedSubCategoriesList.clear()

                selectedSubCategoriesList.addAll(response)



            }
        }


     }
 }

 fun getCategories(){

     viewModelScope.launch {

           val response = repository.getAllCategories()

           if (response?.isNotEmpty()==true){

               categoriesList.addAll(response)

           }
       }


    }

 fun onCategoryClick(id:String?){
     if (id != null) {

     }
     getSubCategories(id)


 }

}