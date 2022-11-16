package com.rotemyanco.brandysestore.adapters


interface OnItemClick<T> {
	fun onItemClick(item: T)
}





/*class onItemClickImp<T> : onItemClick<T> {
	override fun onItemClick(item: T) {
			mBaseProduct = baseProduct
			Log.d(logTag, "onViewCreated:          baseProduct.id  ------  ${baseProduct.productId} -------")

			val b = Bundle()
			b.putString("PRODUCT_ID", baseProduct.productId)
			findNavController().navigate(R.id.action_categoryProductsFragment_to_productDetailsFragment, b)
		}
} */