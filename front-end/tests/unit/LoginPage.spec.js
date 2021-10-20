import Vue from 'vue'
import LoginPage from '@/views/LoginPage'

let Constructor
let vm

describe('LoginPage.vue', () => {
  it('제목이 제대로 출력되는지 확인한다.', () => {
    Constructor = Vue.extend(LoginPage)
    vm = new Constructor().$mount()

    expect(vm.$el.querySelector('h1').textContent)
      .toEqual('TaskAgile')
  })
})
